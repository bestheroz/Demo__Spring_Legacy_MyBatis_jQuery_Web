package com.github.bestheroz.sample.context.db;

import com.github.bestheroz.standard.context.db.DbMybatisContext;
import com.github.bestheroz.standard.override.SqlSessionTemplateOverride;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.github.bestheroz", annotationClass = Mapper.class)
public class H2dbMybatisContext {
    private static final EmbeddedDatabase EMBEDDED_DATABASE = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("/sample/DB/MySqlNH2/mysql-h2-db.sql").build();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        this.logger.info("H2 dataSource :: init");
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        return EMBEDDED_DATABASE;
    }

    @Bean(name = "sqlSession", destroyMethod = "clearCache")
    @Resource
    @Primary
    public SqlSessionTemplateOverride getMySqlSessionTemplate(final ApplicationContext applicationContext) throws Exception {
        return new SqlSessionTemplateOverride(DbMybatisContext.getSqlSessionFactoryBean(applicationContext, this.getDataSource()).getObject());
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager getPlatformTransactionManager() {
        return new DataSourceTransactionManager(this.getDataSource());
    }
}
