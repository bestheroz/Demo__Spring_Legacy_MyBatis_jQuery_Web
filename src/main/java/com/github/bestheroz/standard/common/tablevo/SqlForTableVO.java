package com.github.bestheroz.standard.common.tablevo;

import com.github.bestheroz.standard.common.exception.CommonException;
import com.github.bestheroz.standard.common.exception.CommonExceptionCode;
import com.github.bestheroz.standard.common.util.MyMapperUtils;
import com.github.bestheroz.standard.common.util.MyNullUtils;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("ALL")
public class SqlForTableVO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    public static final String COUNT = "countTableVO";
    public static final String SELECT = "selectTableVO";
    public static final String SELECT_ONE = "selectOneTableVO";
    public static final String INSERT = "insertTableVO";
    public static final String UPDATE = "updateTableVO";
    public static final String DELETE = "deleteTableVO";
    private static final String TABLE_COLUMN_NAME_REG_ID = "REG_ID";
    private static final String TABLE_COLUMN_NAME_REG_DT = "REG_DT";
    private static final String TABLE_COLUMN_NAME_UPDT_DT = "UPD_DT";
    private static final String VARIABLE_NAME_REG_ID = "regId";
    private static final String VARIABLE_NAME_REG_DT = "regDt";
    private static final String VARIABLE_NAME_UPDT_DT = "updDt";
    private static final String SYSDATE = "SYSDATE";

    private static final String ENCRYPTED_FIELD_LIST = "ENCRYPTED_COLUMN_LIST";
    private static final Set<String> EXCLUDE_FIELD_LIST = Sets.newHashSet("SERIAL_VERSION_U_I_D", "serialVersionUID", "E_N_C_R_Y_P_T_E_D__C_O_L_U_M_N__L_I_S_T");
    // 참고용: 각VO에 암호화 컬럼 정의 방법
    // public static transient final Set<String> ENCRYPTED_COLUMN_LIST = Sets.newHashSet("mbrMobl", "emailId").stream().collect(Collectors.toSet());
    private static final String SELECT_ENCRYPTED_STRING = "XX1.DEC_VARCHAR2_SEL ({0}, 10, ''SSN'', ''{1}'', ''{0}'') AS {0}";
    private static final String INSERT_BIND_STRING = "#'{'{0}{1}'}'";
    private static final String INSERT_BIND_ENCRYPTED_STRING = "XX1.ENC_VARCHAR2_INS (#'{'{1}{2}'}', 11, ''SSN'', ''{3}'', ''{0}'')";
    private static final String SET_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String SET_BIND_ENCRYPTED_STRING = "{0} = XX1.ENC_VARCHAR2_INS (#'{'param1.{1}{2}'}', 11, ''SSN'', ''{3}'', ''{0}'')";
    private static final String WHERE_BIND_STRING = "{0} = #'{'param1.{1}{2}'}'";
    private static final String WHERE_BIND_ENCRYPTED_STRING = "{0} = XX1.ENC_VARCHAR2_INS (#'{'param1.{1}{2}'}', 11, ''SSN'', ''{3}'', ''{0}'')";

    public <T extends Object> String countTableVO(final T vo, final Set<String> whereKeys) {
        final SQL sql = new SQL();
        final String tableName = getTableName(vo);
        sql.SELECT("COUNT(1) AS CNT").FROM(tableName);
        if (MyNullUtils.isNotEmpty(whereKeys)) {
            getWhereSql(vo, whereKeys, sql, tableName);
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    private <T extends Object> void getSelectSql(final T vo, final SQL sql, final String tableName, final Field[] fields) {
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Field field : fields) {
            final String camelFieldName = field.getName();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else if (encryptedColumnList.contains(camelFieldName)) {
                sql.SELECT(MessageFormat.format(SELECT_ENCRYPTED_STRING, fieldName, tableName));
            } else {
                sql.SELECT(fieldName);
            }
        }
    }

    private void validWhereKey(final Set<String> whereKeys, final Map<String, Object> param) {
        if (MyNullUtils.size(whereKeys) < 1) {
            this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
            throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
        }

        for (final String key : whereKeys) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn(CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
            }
        }
    }

    private <T extends Object> void getWhereSql(final T vo, final Set<String> whereKeys, final SQL sql, final String tableName) {
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        try {
            validWhereKey(whereKeys, param);
        } catch (Exception e) {
            // pass
        }
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String camelFieldName = entry.getKey();
            if (whereKeys.contains(camelFieldName)) {
                final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                if (encryptedColumnList.contains(camelFieldName)) {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                } else {
                    sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Object> Set<String> getEncryptedColumnList(final T vo) {
        try {
            return (Set<String>) vo.getClass().getField(ENCRYPTED_FIELD_LIST).get(new HashSet<>());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            return new HashSet<>();
        }
    }

    @SuppressWarnings("unused")
    public <T extends Object> String selectTableVO(final T vo, final Set<String> whereKeys, final String orderByColumns) {
        final SQL sql = new SQL();
        final String tableName = getTableName(vo);
        final Field[] fields = vo.getClass().getDeclaredFields();
        getSelectSql(vo, sql, tableName, fields);
        sql.FROM(tableName);

        if (MyNullUtils.isNotEmpty(whereKeys)) {
            getWhereSql(vo, whereKeys, sql, tableName);
        }
        if (StringUtils.isNotEmpty(orderByColumns)) {
            sql.ORDER_BY(orderByColumns);
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    private <T extends Object> String getTableName(final T vo) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(vo.getClass().getSimpleName(), "Table", "VO"));
    }

    public <T extends Object> String selectOneTableVO(final T vo, final Set<String> whereKeys) {
        validWhereKey(whereKeys, MyMapperUtils.writeObjectAsHashMap(vo));

        final SQL sql = new SQL();
        final String tableName = getTableName(vo);
        final Field[] fields = vo.getClass().getDeclaredFields();
        getSelectSql(vo, sql, tableName, fields);
        sql.FROM(tableName);
        getWhereSql(vo, whereKeys, sql, tableName);
        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String insertTableVO(final T vo) {
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        final SQL sql = new SQL();
        final String tableName = getTableName(vo);
        sql.INSERT_INTO(tableName);
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Entry<String, Object> entry : param.entrySet()) {
            final String camelFieldName = entry.getKey();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (StringUtils.equalsAny(camelFieldName, VARIABLE_NAME_REG_DT, VARIABLE_NAME_UPDT_DT)) {
                sql.VALUES(fieldName, SYSDATE);
            } else {
                final String columnTypeName = entry.getValue().getClass().getSimpleName();
                if (encryptedColumnList.contains(camelFieldName)) {
                    sql.VALUES(fieldName, MessageFormat.format(INSERT_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                } else {
                    sql.VALUES(fieldName, MessageFormat.format(INSERT_BIND_STRING, camelFieldName, this.getJdbcType(columnTypeName)));
                }
            }
        }

        final Class<? extends Object> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String camelFieldName = field.getName();
            if (EXCLUDE_FIELD_LIST.contains(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName))) {
                continue;
            } else if (StringUtils.equals(camelFieldName, VARIABLE_NAME_REG_DT)) {
                sql.VALUES(TABLE_COLUMN_NAME_REG_DT, SYSDATE);
            } else if (StringUtils.equals(camelFieldName, VARIABLE_NAME_UPDT_DT)) {
                sql.VALUES(TABLE_COLUMN_NAME_UPDT_DT, SYSDATE);
            }
        }

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String updateTableVO(final T vo, final Set<String> whereKeys, final Set<String> forcedUpdateKey) {
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        validWhereKey(whereKeys, param);

        final SQL sql = new SQL();
        final String tableName = getTableName(vo);
        sql.UPDATE(tableName);
        final Field[] fields = vo.getClass().getDeclaredFields();
        final Set<String> encryptedColumnList = this.getEncryptedColumnList(vo);
        for (final Field field : fields) {
            final String camelFieldName = field.getName();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else if (StringUtils.equalsAny(camelFieldName, VARIABLE_NAME_REG_ID, VARIABLE_NAME_REG_DT, VARIABLE_NAME_UPDT_DT)) {
                continue;
            }
            final String columnTypeName = field.getType().getSimpleName();
            if (forcedUpdateKey != null && !whereKeys.contains(camelFieldName) && (forcedUpdateKey.contains("**") || forcedUpdateKey.contains(camelFieldName))
                    && !StringUtils.equalsAny(camelFieldName, TABLE_COLUMN_NAME_REG_ID, TABLE_COLUMN_NAME_REG_DT)) {
                if (encryptedColumnList.contains(camelFieldName)) {
                    sql.SET(MessageFormat.format(SET_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                } else {
                    sql.SET(MessageFormat.format(SET_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                }
            } else if (param.get(camelFieldName) != null && StringUtils.isNotEmpty(param.get(camelFieldName).toString())) {
                if (whereKeys.contains(camelFieldName)) {
                    if (encryptedColumnList.contains(camelFieldName)) {
                        sql.WHERE(MessageFormat.format(WHERE_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                    } else {
                        sql.WHERE(MessageFormat.format(WHERE_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                    }
                } else if (StringUtils.equalsAny(camelFieldName, VARIABLE_NAME_REG_DT, VARIABLE_NAME_UPDT_DT)) {
                    sql.SET(fieldName + " = " + SYSDATE);
                } else {
                    if (encryptedColumnList.contains(camelFieldName)) {
                        sql.SET(MessageFormat.format(SET_BIND_ENCRYPTED_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName), tableName));
                    } else {
                        sql.SET(MessageFormat.format(SET_BIND_STRING, fieldName, camelFieldName, this.getJdbcType(columnTypeName)));
                    }
                }
            }
        }

        final Class<? extends Object> class1 = vo.getClass();
        for (final Field field : class1.getDeclaredFields()) {
            final String camelFieldName = field.getName();
            final String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, camelFieldName);
            if (EXCLUDE_FIELD_LIST.contains(fieldName)) {
                continue;
            } else if (StringUtils.equals(camelFieldName, VARIABLE_NAME_UPDT_DT)) {
                sql.SET(TABLE_COLUMN_NAME_UPDT_DT + " = " + SYSDATE);
            }
        }
        this.logger.debug(sql.toString());
        return sql.toString();
    }

    public <T extends Object> String deleteTableVO(final T vo, final Set<String> whereKeys) {
        if (MyNullUtils.size(whereKeys) < 1) {
            this.logger.warn(CommonExceptionCode.ERROR_NO_DATA_SUCCESS.toString());
            throw CommonException.EXCEPTION_ERROR_NO_DATA_SUCCESS;
        }
        final Map<String, Object> param = MyMapperUtils.writeObjectAsHashMap(vo);
        for (final String key : whereKeys) {
            if (!param.containsKey(key) || param.get(key) == null) {
                this.logger.warn("{} not in {}\n{}", key, MyMapperUtils.writeObjectAsString(param), CommonExceptionCode.ERROR_INVALID_PARAMETER.toString());
                throw CommonException.EXCEPTION_ERROR_INVALID_PARAMETER;
            }
        }
        final SQL sql = new SQL();
        final String tableName = getTableName(vo);
        sql.DELETE_FROM(tableName);
        getWhereSql(vo, whereKeys, sql, tableName);

        this.logger.debug(sql.toString());
        return sql.toString();
    }

    private String getJdbcType(final String columnTypeName) {
        String jdbcType;
        if (StringUtils.equalsAny(columnTypeName, "String")) {
            jdbcType = ", jdbcType=VARCHAR";
        } else if (StringUtils.equalsAny(columnTypeName, "Short")) {
            jdbcType = ", jdbcType=SMALLINT";
        } else if (StringUtils.equalsAny(columnTypeName, "Integer")) {
            jdbcType = ", jdbcType=INTEGER";
        } else if (StringUtils.equalsAny(columnTypeName, "Long")) {
            jdbcType = ", jdbcType=BIGINT";
        } else if (StringUtils.equalsAny(columnTypeName, "DateTime", "LocalDateTime")) {
            jdbcType = ", jdbcType=TIMESTAMP";
        } else if (StringUtils.equalsAny(columnTypeName, "BLOB")) {
            jdbcType = ", jdbcType=BLOB";
        } else {
            jdbcType = "";
            this.logger.warn("케이스 빠짐 {}", columnTypeName);
        }
        return jdbcType;
    }
}