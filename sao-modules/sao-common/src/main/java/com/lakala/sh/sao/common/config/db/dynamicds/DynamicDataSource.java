//package com.lakala.sh.sao.common.config.db;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
///**
// * @author steellee
// * @date 2018/06/28
// */
//public class DynamicDataSource extends AbstractRoutingDataSource {
//    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        log.debug("数据源为{}", DataSourceContextHolder.getDB());
//
//        return DataSourceContextHolder.getDB();
//    }
//}
