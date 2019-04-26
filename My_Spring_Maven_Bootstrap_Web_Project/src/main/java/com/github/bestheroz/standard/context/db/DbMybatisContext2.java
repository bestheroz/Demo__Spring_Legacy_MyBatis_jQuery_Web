package com.github.bestheroz.standard.context.db;

import com.github.bestheroz.standard.override.SqlSessionTemplateOverride;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;

//@Configuration
//@EnableTransactionManagement
//@MapperScan(basePackages = "com.github.bestheroz", annotationClass = Mapper.class)
public class DbMybatisContext2 {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("#{jdbcProperties['jdbc.second.driverClassName']}")
    private String driverClassName;
    @Value("#{jdbcProperties['jdbc.second.url']}")
    private String url;
    @Value("#{jdbcProperties['jdbc.second.username']}")
    private String username;
    @Value("#{jdbcProperties['jdbc.second.password']}")
    private String password;
    @Value("#{jdbcProperties['jdbc.second.autoCommit']}")
    private Boolean autoCommit;
    @Value("#{jdbcProperties['jdbc.second.connectionTimeoutMs']}")
    private Long connectionTimeoutMs;
    @Value("#{jdbcProperties['jdbc.second.idleTimeoutMs']}")
    private Long idleTimeoutMs;
    @Value("#{jdbcProperties['jdbc.second.maxLifetimeMs']}")
    private Long maxLifetimeMs;
    @Value("#{jdbcProperties['jdbc.second.minIdle']}")
    private Integer minIdle;
    @Value("#{jdbcProperties['jdbc.second.maxPoolSize']}")
    private Integer maxPoolSize;

    @Bean(name = "dataSource2")
    public HikariDataSource getHikariDataSource() {
        this.logger.info("dataSource2 :: init");
        return new HikariDataSource(
                DbMybatisContext.getHikariConfig(this.driverClassName, this.url, this.username, this.password, this.autoCommit, this.connectionTimeoutMs, this.idleTimeoutMs, this.maxLifetimeMs
                        , this.minIdle, this.maxPoolSize));
    }

    /**
     * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
     * <p>
     * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다. 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
     */
    @Bean(name = "sqlSession2", destroyMethod = "clearCache")
    @Resource
    public SqlSessionTemplateOverride getMySqlSessionTemplate(final ApplicationContext applicationContext) throws Exception {
        return new SqlSessionTemplateOverride(DbMybatisContext.getSqlSessionFactoryBean(applicationContext, this.getHikariDataSource()).getObject());
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new DataSourceTransactionManager(this.getHikariDataSource());
    }
}