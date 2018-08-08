package com.lakala.sh.sa.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 配置tokenEndpoint
 * @author steellee
 * @date 2018/08/08
 */
@RestController
public class UserController {

    /**
     * 获取当前用户对象
     * @param principal
     * @return
     */
    @GetMapping(value = "/current") public Principal getUser(Principal principal) {
        return principal;
    }
}