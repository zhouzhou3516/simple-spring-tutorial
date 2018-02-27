package com.qunar.campus.spring.tutorial.controller;

import com.qunar.campus.spring.tutorial.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description: UserController
 *
 * @author yushen.ma
 * @version 2015-04-02 00:48
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String userLogin(String username, String password, HttpServletRequest request) {
        if (null != userService.check(username, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
        }
        return "access deny";
    }
}
