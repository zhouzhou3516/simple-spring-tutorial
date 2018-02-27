package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: Case2RequestMapping
 *
 * 需要一点点基础的HTTP知识和概念
 *
 * 你需要明白GET,POST,PUT,DELETE其含义以及他们之间的差别
 *
 * 你需要明白协议的含义，协议header，协议body的意义
 *
 * extension:
 * Request Header, Accept, Accept-Encoding, Connection(lit bit hard), Content-Type(what's this)...
 * Response Head, Content-Length(why need this)...
 *
 * @author yushen.ma
 * @version 2015-03-28 17:11
 */
@Controller
@RequestMapping("/mapping")
public class Case2RequestMapping {

    /**
     * 一个只支持POST请求的mapping,
     * GET方法同理
     *
     * 可以考虑以下在什么场景下面必须用GET,什么场景下必须用POST，为什么？
     * @return String
     */
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public String onlyPost() {
        return "I'm only support post method";
    }

    /**
     * application/json是个什么东西呢
     *
     * 只支持请求content-type类型为：application/json
     * 通配符的形式在这里同样可以支持，例如:application/*
     * @return String
     */
    @RequestMapping(value = "/json", consumes = "application/json")
    public String onlyJson() {
        return "I'm only support json application";
    }

    /**
     * 只支持，请求的Accept里面包含application/json的
     * 通常情况下，Chrome在未设置的情况下
     * Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*;q=0.8
     */
    @RequestMapping(value = "/json", produces = "application/json")
    public String onlyAcceptJson() {
        return "{ 'info' : 'I only produce json response'}";
    }

}
