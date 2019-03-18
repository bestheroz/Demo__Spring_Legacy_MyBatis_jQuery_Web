package com.github.bestheroz.standard.context.init.dbchecker;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
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

import com.google.common.base.CaseFormat;

//@SpringJUnitConfig(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
//@WebAppConfiguration
//@Transactional
public class TestDbTableVOCheckerContext {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SqlSession sqlSession;

	// @Test
	public void validDbTableVO() {
		try (Statement stmt = this.sqlSession.getConnection().createStatement()) {
			final List<Class<?>> targetClassList = this.findMyTypes("com.github.bestheroz");
			final List<String> filedList = new ArrayList<>();
			for (final Class<?> class1 : targetClassList) {
				filedList.clear();
				for (final Field field : class1.getDeclaredFields()) {
					filedList.add(field.getName());
				}
				final String tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, StringUtils.substringBetween(class1.getSimpleName(), "TestTable", "VO"));
				try (ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE ROWNUM=0")) {
					final ResultSetMetaData metaInfo = rs.getMetaData();
					final String className = class1.getSimpleName();

					// 1. VO변수 개수 == 테이블 컬럼 개수 체크
					if (metaInfo.getColumnCount() != filedList.size()) {
						this.logger.warn("{} 변수 개수({}) != ({}){} 컬럼 개수", className, metaInfo.getColumnCount(), tableName, filedList.size());
						continue;
					}

					// 2. VO변수 자료형 == 테이블 컬럼 자료형 체크
					for (int i = 0; i < metaInfo.getColumnCount(); i++) {
						final String columnName = metaInfo.getColumnName(i + 1);
						final String camelColumnName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
						if (filedList.contains(camelColumnName)) {
							final String fieldClassName = class1.getDeclaredField(camelColumnName).getType().getSimpleName();
							final String columnTypeName = metaInfo.getColumnTypeName(i + 1);
							if (StringUtils.equalsAny(columnTypeName, "VARCHAR", "CHAR", "CLOB") && !StringUtils.equals(fieldClassName, "String")
									|| StringUtils.equalsAny(columnTypeName, "INTEGER", "NUMBER") && !StringUtils.equalsAny(fieldClassName, "Integer", "Double", "Long")
									|| StringUtils.equalsAny(columnTypeName, "TIMESTAMP", "DATE") && !StringUtils.equals(fieldClassName, "DateTime")
									|| StringUtils.equalsAny(columnTypeName, "BLOB") && !StringUtils.equals(fieldClassName, "Byte[]")) {
								this.logger.warn("자료형이 일치하지 않음 {}.{}({}) != {}.{}({})", tableName, columnName, columnTypeName, className, camelColumnName, fieldClassName);
							}
						} else {
							this.logger.warn("VO에 해당컬럼없음 {}.{} : {}.{}", tableName, columnName, className, camelColumnName);
						}
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
