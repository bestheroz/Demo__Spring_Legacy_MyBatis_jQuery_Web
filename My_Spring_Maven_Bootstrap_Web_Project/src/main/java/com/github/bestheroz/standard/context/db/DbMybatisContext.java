package com.github.bestheroz.standard.context.db;

import com.github.bestheroz.standard.override.SqlSessionTemplateOverride;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;

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
    public HikariDataSource getHikariDataSource() {
        this.logger.info("dataSource :: init");
        return new HikariDataSource(
                getHikariConfig(this.driverClassName, this.url, this.username, this.password, this.autoCommit, this.connectionTimeoutMs, this.idleTimeoutMs, this.maxLifetimeMs
                        , this.minIdle, this.maxPoolSize));
    }

    public static HikariConfig getHikariConfig(final String driverClassName, final String url, final String username, final String password, final Boolean autoCommit, final Long connectionTimeoutMs,
                                               final Long idleTimeoutMs, final Long maxLifetimeMs, final Integer minIdle, final Integer maxPoolSize) {
        final HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        // hikariConfig.setUsername(AES.decrypt128(username));
        hikariConfig.setUsername(username);
        // hikariConfig.setPassword(AES.decrypt128(password));
        hikariConfig.setPassword(password);
        hikariConfig.setAutoCommit(autoCommit);
        hikariConfig.setConnectionTimeout(connectionTimeoutMs);
        hikariConfig.setIdleTimeout(idleTimeoutMs);
        hikariConfig.setMaxLifetime(maxLifetimeMs);
        hikariConfig.setMinimumIdle(minIdle);
        hikariConfig.setMaximumPoolSize(maxPoolSize);
        return hikariConfig;
    }

    /**
     * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     * <p>
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
     */
    @Bean(name = "sqlSession", destroyMethod = "clearCache")
    @Resource
    @Primary
    public SqlSessionTemplateOverride getMySqlSessionTemplate(final ApplicationContext applicationContext) throws Exception {
        return new SqlSessionTemplateOverride(getSqlSessionFactoryBean(applicationContext, this.getHikariDataSource()).getObject());
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new DataSourceTransactionManager(this.getHikariDataSource());
    }

    public static SqlSessionFactoryBean getSqlSessionFactoryBean(final ApplicationContext applicationContext, final DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setTypeAliasesPackage("com.github.bestheroz.sample");
        sqlSessionFactory.setTypeAliasesSuperType(Serializable.class);
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:**/*Mapper.xml"));
        return sqlSessionFactory;
    }

}
