package com.lakala.sh.sao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ApplicationContext;

/**
 * 从Edgware版本开始新增的一种配置方式：采用数据库存储配置信息
 *
 */
@EnableConfigServer		// 开启Spring Cloud Config的服务端功能
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ConfigServerApplication.class);
		/*
		// 测试用数据，仅用于本文测试使用
		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		jdbcTemplate.execute("delete from sys_config_properties");
		jdbcTemplate.execute("INSERT INTO sys_config_properties VALUES(1, 'com.sao.message', 'test-stage-master', 'cmbc-service', 'stage', 'master')");
		jdbcTemplate.execute("INSERT INTO sys_config_properties VALUES(2, 'com.sao.message', 'test-online-master', 'config-client', 'dev', 'master')");
		jdbcTemplate.execute("INSERT INTO sys_config_properties VALUES(3, 'com.sao.message', 'test-online-develop', 'config-client', 'online', 'develop')");
		jdbcTemplate.execute("INSERT INTO sys_config_properties VALUES(4, 'com.sao.message', 'hello-online-master', 'hello-service', 'online', 'master')");
		jdbcTemplate.execute("INSERT INTO sys_config_properties VALUES(5, 'com.sao.message', 'hello-online-develop', 'hello-service', 'online', 'develop')");
		*/
	}
}
