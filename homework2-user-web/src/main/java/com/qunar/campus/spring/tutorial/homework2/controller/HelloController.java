package com.qunar.campus.spring.tutorial.homework2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {


    @RequestMapping("hello")
    public ModelAndView hello() {
        ModelAndView model = new ModelAndView("../homePage");
        model.addObject("msg", "hello world");

        return model;

    }

    @RequestMapping("ex")
    public ModelAndView ex() {
        throw new RuntimeException("测试异常");
    }

    @RequestMapping("sayhi")
    @ResponseBody
    public Object sayhi(@RequestParam("name") String name) {
        return "eeee";
    }
}