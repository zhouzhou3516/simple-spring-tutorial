package com.qunar.campus.spring.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: HelloController
 *
 * @author yushen.ma
 * @version 2015-04-04 18:01
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/say")
    @ResponseBody
    public String sayHello() {
        return "hello";
    }


    public String saveOrder(@RequestParam String username) {
        Assert.isTrue(username == null || username.isEmpty());
        return null;
    }
}
