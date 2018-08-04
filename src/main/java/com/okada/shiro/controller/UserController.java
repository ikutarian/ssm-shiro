package com.okada.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, new SimpleHash("MD5", password, "salt", 1024).toString());
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException ex) {
                System.out.println("登陆失败：" + ex.getMessage());
            }
        }

        return "redirect:/list.jsp";
    }
}
