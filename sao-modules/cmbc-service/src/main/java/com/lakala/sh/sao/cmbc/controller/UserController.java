package com.lakala.sh.sao.cmbc.controller;

import com.lakala.sh.sao.common.utils.IdUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Demo用
 * 应用场景：结合spring-session测试session的redis存储及集群
 */
@Controller
public class UserController {

    @RequestMapping(value="/main", method= RequestMethod.GET)
    public String main(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionId");
        if (null != sessionId) { // sessionId不为空
            System.out.println("main sessionId:" + sessionId);
            return "main";
        } else { // sessionId为空
            return "redirect:/login";
        }
    }


    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 模拟登录
     * @param request
     * @return
     */
    @RequestMapping(value="/doLogin", method=RequestMethod.POST)
    public String doLogin(HttpServletRequest request) {
        System.out.println("I do real login here");
        HttpSession session = request.getSession();
        String sessionId = IdUtil.getUUID().toString();
        session.setAttribute("sessionId", sessionId);
        session.setAttribute("test1", "ligang123456789");
        System.out.println("login sessionId:" + sessionId);
        return "redirect:/main";
    }
}
