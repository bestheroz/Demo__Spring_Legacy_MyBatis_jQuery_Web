package com.github.bestheroz.standard.context.db;

import java.io.Serializable;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.bestheroz.standard.override.SqlSessionTemplateOverride;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages = "com.github.bestheroz", annotationClass = Mapper.class)
public class DbMybatisContext {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("#{jdbcProperties['jdbc.first.driverClassName']}")
	private String driverClassName;
	@Value("#{jdbcProperties['jdbc.first.url']}")
	private String url;
	@Value("#{jdbcProperties['jdbc.first.username']}")
	private String username;
	@Value("#{jdbcProperties['jdbc.first.password']}")
	private String password;
	@Value("#{jdbcProperties['jdbc.first.autoCommit']}")
	private Boolean autoCommit;
	@Value("#{jdbcProperties['jdbc.first.connectionTimeoutMs']}")
	private Long connectionTimeoutMs;
	@Value("#{jdbcProperties['jdbc.first.idleTimeoutMs']}")
	private Long idleTimeoutMs;
	@Value("#{jdbcProperties['jdbc.first.maxLifetimeMs']}")
	private Long maxLifetimeMs;
	@Value("#{jdbcProperties['jdbc.first.minIdle']}")
	private Integer minIdle;
	@Value("#{jdbcProperties['jdbc.first.maxPoolSize']}")
	private Integer maxPoolSize;

	@Bean(name = "dataSource")
	public HikariDataSource getHikariDataSource() throws Exception {
		this.logger.info("dataSource :: init");
		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(this.driverClassName);
		hikariConfig.setJdbcUrl(this.url);
		// hikariConfig.setUsername(AES.decrypt128(this.username));
		hikariConfig.setUsername(this.username);
		// hikariConfig.setPassword(AES.decrypt128(this.password));
		hikariConfig.setPassword(this.password);
		hikariConfig.setAutoCommit(this.autoCommit);
		hikariConfig.setConnectionTimeout(this.connectionTimeoutMs);
		hikariConfig.setIdleTimeout(this.idleTimeoutMs);
		hikariConfig.setMaxLifetime(this.maxLifetimeMs);
		hikariConfig.setMinimumIdle(this.minIdle);
		hikariConfig.setMaximumPoolSize(this.maxPoolSize);

		return new HikariDataSource(hikariConfig);
	}

	/**
	 * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
	 *
	 * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
	 *
	 */
	@Bean(name = "sqlSession", destroyMethod = "clearCache")
	@Autowired
	@Primary
	public SqlSessionTemplateOverride getMySqlSessionTemplate(final ApplicationContext applicationContext) throws Exception {
		return new SqlSessionTemplateOverride(this.getSqlSessionFactoryBean(applicationContext).getObject());
	}

	@Bean(name = "transactionManager")
	@Primary
	public PlatformTransactionManager getPlatformTransactionManager() throws Exception {
		return new DataSourceTransactionManager(this.getHikariDataSource());
	}

	private SqlSessionFactoryBean getSqlSessionFactoryBean(final ApplicationContext applicationContext) throws Exception {
		final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(this.getHikariDataSource());
		sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
		sqlSessionFactory.setTypeAliasesPackage("com.github.bestheroz");
		sqlSessionFactory.setTypeAliasesSuperType(Serializable.class);
		sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:**/*Mapper.xml"));
		return sqlSessionFactory;
	}

}
