//package com.lakala.sh.sao.common.config.db.shardingsphere;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import io.shardingsphere.core.api.MasterSlaveDataSourceFactory;
//import io.shardingsphere.core.api.config.MasterSlaveRuleConfiguration;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ShardingDataSource {
//
//        /**
//         * 配置@Transactional注解事物
//         * @return
//         */
//        /*@Primary
//        @Bean
//        public PlatformTransactionManager transactionManager() {
//            return new DataSourceTransactionManager(getShardingDataSource());
//        }*/
//
////    @Primary
//    @Bean
////    @Qualifier("shardingDataSource")
//    public DataSource getShardingDataSource() {
//        // 配置真实数据源
//        Map<String, DataSource> dataSourceMap = new HashMap<>();
//
//        // 配置第一个数据源
//        DruidDataSource dataSource1 = createDefaultDruidDataSource();
//        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource1.setUrl("jdbc:mysql://localhost:3306/saodb?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false");
//        dataSource1.setUsername("root");
//        dataSource1.setPassword("admin");
//        dataSourceMap.put("localdb", dataSource1);
//
//        // 配置第二个数据源
//        DruidDataSource dataSource2 = createDefaultDruidDataSource();
//        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource2.setUrl("jdbc:mysql://localhost:3306/saodb?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false");
//        dataSource2.setUsername("root");
//        dataSource2.setPassword("admin");
//        dataSourceMap.put("178db", dataSource2);
//
//        // 配置第三个数据源
//       /* DruidDataSource dataSource3 = createDefaultDruidDataSource();
//        dataSource3.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        dataSource3.setUrl("jdbc:mysql://localhost:3306/db2");
//        dataSource3.setUsername("root");
//        dataSource3.setPassword("root");
//        dataSourceMap.put("db2", dataSource3);*/
//
//
//        // 配置Order表规则
////        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
////        orderTableRuleConfig.setLogicTable("t_order");
////        orderTableRuleConfig.setActualDataNodes("db${0..2}.t_order_${0..1}");
//        //orderTableRuleConfig.setActualDataNodes("db0.t_order_0,db0.t_order_1,db1.t_order_0,db1.t_order_1,db2.t_order_0,db2.t_order_1");
//
//        // 配置分库策略（Groovy表达式配置db规则）
////        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "db${user_id % 3}"));
//
//        // 配置分表策略（Groovy表达式配置表路由规则）
////        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id", "t_order_${order_id % 2}"));
//
//        // 配置分片规则
////        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
////        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
//
//        // 配置order_items表规则...
//
//        // 获取数据源对象
//        /*DataSource dataSource = null;
//        try {
//            dataSource = ShardingDataSourceFactory
//                .createDataSource(dataSourceMap, shardingRuleConfig, new ConcurrentHashMap(), new Properties());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }*/
//
//        // 配置读写分离规则
//        MasterSlaveRuleConfiguration
//            masterSlaveRuleConfig = new MasterSlaveRuleConfiguration("ds_master_slave", "localdb", Arrays
//            .asList("178db"));
//
//        // 获取数据源对象
//        DataSource dataSource = null;
//        try {
//            dataSource = MasterSlaveDataSourceFactory
//                .createDataSource(dataSourceMap, masterSlaveRuleConfig, new HashMap<String, Object>());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }
//
//    public DruidDataSource createDefaultDruidDataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        druidDataSource.setMaxWait(60000l);
//        druidDataSource.setMaxActive(20);
//        druidDataSource.setInitialSize(1);
//        druidDataSource.setMinIdle(1);
//        druidDataSource.setTimeBetweenEvictionRunsMillis(3000l);
//        druidDataSource.setMinEvictableIdleTimeMillis(300000l);
//        druidDataSource.setConnectionProperties("druid.stat.slowSqlMillis=3000");
//        druidDataSource.setValidationQuery("SELECT 'x'");
//        druidDataSource.setTestWhileIdle(true);
//        druidDataSource.setTestOnBorrow(false);
//        druidDataSource.setTestOnReturn(false);
//        return druidDataSource;
//    }
//}
