//package com.lakala.sh.sao.common.config.db;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author steellee
// * @date 2018/06/28
// */
//@Configuration
//public class MultiDataSourceConfig {
//
//    ////    @Primary
//    @Bean(name = "178dbDS")
////    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.178db")
//    public DataSource dataSourceOne(){
//        return DruidDataSourceBuilder.create().build();
//    }
//    //
//    @Bean(name = "localdbDS")
////    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.druid.localdb")   // application.properteis中对应属性的前缀
//    public DataSource dataSourceTwo(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//
//    @Primary
//    @Bean(name = "dynamicDataSource")
//    public DataSource dynamicDataSource() {
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        // 默认数据源
//        dynamicDataSource.setDefaultTargetDataSource(dataSourceTwo());
//
//
//        // 配置多数据源
//        Map<Object, Object> dsMap = new HashMap(5);
//        dsMap.put("178dbDS", dataSourceOne());
//        dsMap.put("localdbDS", dataSourceTwo());
//
//        dynamicDataSource.setTargetDataSources(dsMap);
//
//        return dynamicDataSource;
//    }
//
//    /**
//     * 配置@Transactional注解事物
//     * @return
//     */
//    /*@Bean
//    public PlatformTransactionManager transactionManager() {
//        return new DataSourceTransactionManager(dynamicDataSource());
//    }*/
//}
