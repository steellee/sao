package com.lakala.sh.sao.cmbc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Demo用
 * 应用场景：测试配置中心，从db/git取得相关配置属性值
 *
 * (springboot2.0以后版本)
 * 实现配置的刷新:
 * 1, 新增spring-boot-starter-actuator监控模块，其中包含了/refresh刷新API
 * 2，允许actuator配置自动刷新refresh接口，并且所在配置类里加@RefreshScope
 * 3, Content-Type为application/json，POST发送到http://localhost:8080/sao/actuator/refresh
 */

@RestController
@RefreshScope    // 开启refresh机制， 需要给加载变量的类上面加载@RefreshScope注解，其它代码可不做任何改变
public class ConfigClient {

    //	#远程属性：db/git对应key
    @Value("${com.sao.message}")
    private String infoFrom;

    //	#本地属性
    @Value("${profileLocal}")
    private String profileLocal;

    @RequestMapping("config")
    public Object test() {
        return infoFrom + " --------- "+profileLocal;
    }
}
