package com.github.bestheroz.standard.context.db.checker;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Component
public class DbTableVOCheckerContext {
    public static final String DEFAULT_DATE_TYPE = "LocalDateTime";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    public void validDbTableVO(final SqlSession sqlSession) {
        try (Statement stmt = new SqlSessionFactoryBuilder().build(sqlSession.getConfiguration()).openSession().getConnection().createStatement()) {
            final List<Class<?>> targetClassList = this.findMyTypes("com.github.bestheroz");
            final List<String> filedList = new ArrayList<>();
            for (final Class<?> class1 : targetClassList) {
                filedList.clear();
                for (final Field field : class1.getDeclaredFields()) {
                    filedList.add(field.getName());
                }
                final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(class1.getSimpleName(), "Table", "VO"));
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE ROWNUM=0")) {
                    final ResultSetMetaData metaInfo = rs.getMetaData();
                    final String className = class1.getSimpleName();

                    boolean isInvalid = false;
                    // 1. VO변수 개수 == 테이블 컬럼 개수 체크
                    if (metaInfo.getColumnCount() != filedList.size()) {
                        this.logger.warn("{} 변수 개수({}) != ({}){} 컬럼 개수", className, filedList.size(), tableName, metaInfo.getColumnCount());
                        isInvalid = true;
                    }
                    if (!isInvalid) {
                        // 2. VO변수 자료형 == 테이블 컬럼 자료형 체크
                        for (int i = 0; i < metaInfo.getColumnCount(); i++) {
                            final String columnName = metaInfo.getColumnName(i + 1);
                            final String camelColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                            if (filedList.contains(camelColumnName)) {
                                final String fieldClassName = class1.getDeclaredField(camelColumnName).getType().getSimpleName();
                                final String columnTypeName = metaInfo.getColumnTypeName(i + 1);
                                if (StringUtils.equalsAny(columnTypeName, "VARCHAR", "VARCHAR2", "CHAR", "CLOB") && !StringUtils.equals(fieldClassName, "String")
                                        || StringUtils.equalsAny(columnTypeName, "INTEGER", "NUMBER") && !StringUtils.equalsAny(fieldClassName, "Integer", "Double", "Long")
                                        || StringUtils.equalsAny(columnTypeName, "TIMESTAMP", "DATE") && !StringUtils.equalsAny(fieldClassName, "DateTime", "LocalDateTime")
                                        || StringUtils.equalsAny(columnTypeName, "BLOB") && !StringUtils.equals(fieldClassName, "Byte[]")) {
                                    this.logger.warn("자료형이 일치하지 않음 {}.{}({}) != {}.{}({})", tableName, columnName, columnTypeName, className, camelColumnName, fieldClassName);
                                    isInvalid = true;
                                }
                            } else {
                                this.logger.warn("VO에 해당컬럼없음 {}.{} : {}.{}", tableName, columnName, className, camelColumnName);
                                isInvalid = true;
                            }

                        }
                    }
                    if (isInvalid) {
                        final StringBuilder voSb = new StringBuilder(className + ".java를 아래값으로 동기화 해주세요.\n");
                        for (int i = 0; i < metaInfo.getColumnCount(); i++) {
                            String fieldType;
                            final String columnTypeName = metaInfo.getColumnTypeName(i + 1);
                            final String columnName = metaInfo.getColumnName(i + 1);
                            final String camelColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
                            if (StringUtils.equalsAny(columnTypeName, "VARCHAR", "VARCHAR2", "CHAR", "CLOB")) {
                                fieldType = "String";
                            } else if (StringUtils.equalsAny(columnTypeName, "NUMBER")) {
                                if (metaInfo.getScale(i + 1) > 0) { // 소수점이 있으면
                                    fieldType = "Double";
                                } else {
                                    final int columnDisplaySize = metaInfo.getColumnDisplaySize(i + 1); // 실제 자리수보다 한자리 더 나옴(소수점 "." 때문일까?)
                                    if (columnDisplaySize <= 5) { // 5자리 보장못함, 4자리 보장
                                        fieldType = "Short"; // –32,768 ~ 32,767
                                    } else if (columnDisplaySize <= 10) { // 10자리 보장못함, 9자리 보장
                                        fieldType = "Integer"; // –2,147,483,648 ~ 2,147,483,647
                                        // } else if (columnDisplaySize < 19) { // 19자리 보장못함, 18자리 보장, 하지만 (오라클) NUMBER형 기본형이 39다.. Long 으로 처리가자.
                                        // fieldType = "Long"; // -9223372036854775808 ~ 9223372036854775807
                                    } else { // 19자리 보장못함, 18자리 보장, 하지만 (오라클) NUMBER형 기본형이 39다.. Long 으로 처리가자.
                                        fieldType = "Long";
                                        // fieldType = "Double";
                                    }
                                }
                            } else if (StringUtils.equalsAny(columnTypeName, "INTEGER")) {
                                fieldType = "Integer";
                            } else if (StringUtils.equalsAny(columnTypeName, "TIMESTAMP", "DATE")) {
                                fieldType = DEFAULT_DATE_TYPE;
                            } else if (StringUtils.equalsAny(columnTypeName, "BLOB")) {
                                fieldType = "Byte[];";
                                this.logger.debug("private Byte[] {}{}", camelColumnName, "; // XXX: spotbugs 피하기 : Arrays.copyOf(value, value.length)");
                            } else {
                                fieldType = "Unknown";
                                this.logger.warn("케이스 빠짐 {} : {}", columnName, columnTypeName);
                            }
                            voSb.append("private ").append(fieldType).append(" ").append(camelColumnName).append(";\n");
                        }
                        this.logger.warn("\n" + voSb.toString() + "\n");
                    }
                } catch (final Throwable e) {
                    this.logger.warn(ExceptionUtils.getStackTrace(e));
                }
            }
            this.logger.debug("Complete TableVOChecker");
        } catch (final Throwable e) {
            this.logger.warn(ExceptionUtils.getStackTrace(e));
        }
    }

    private List<Class<?>> findMyTypes(final String basePackage) throws IOException, ClassNotFoundException {
        final ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        final MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        final List<Class<?>> candidates = new ArrayList<>();
        final String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + this.resolveBasePackage(basePackage) + "/" + "**/Table*VO.class";
        final Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
        for (final Resource resource : resources) {
            if (resource.isReadable()) {
                final MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                // if (this.isCandidate(metadataReader)) {
                candidates.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                // }
            }
        }
        return candidates;
    }

    private String resolveBasePackage(final String basePackage) {
        return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage));
    }

    // private boolean isCandidate(final MetadataReader metadataReader) throws ClassNotFoundException {
    // try {
    // // Class<?> c = Class.forName(metadataReader.getClassMetadata().getClassName());
    // return true;
    // } catch (Throwable e) {
    // // ignored
    // }
    // return false;
    // }
}
