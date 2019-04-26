package com.github.bestheroz.standard.context.db.transaction;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
//@EnableTransactionManagement
public class TransactionContext {

    // @Autowired
    // private H2dbMybatisContext h2dbMybatisContext;
    // @Autowired
    // private DbMybatisContext dbMybatisContext;

    // @Bean(name = "transactionManager")
    // @Autowired
    // @Primary
    public WiredTransactionManager getWiredTransactionManager(final ApplicationContext applicationContext) throws Exception {
        final WiredTransactionManager wiredTransactionManager = new WiredTransactionManager();
        final List<PlatformTransactionManager> transactionManagers = new ArrayList<>();
        // transactionManagers.add(new DataSourceTransactionManager(this.h2dbMybatisContext.dataSource()));
        // transactionManagers.add(new DataSourceTransactionManager(this.dbMybatisContext.dataSource()));
        wiredTransactionManager.setTransactionManagers(transactionManagers);
        return wiredTransactionManager;
    }
}
