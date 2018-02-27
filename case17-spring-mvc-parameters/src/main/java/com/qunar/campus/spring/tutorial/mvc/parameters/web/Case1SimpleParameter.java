package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * Description: SimpleParameter
 *
 * 这里主要介绍如何传简单的参数
 *
 * @author yushen.ma
 * @version 2015-03-28 16:21
 */
@Controller
@RequestMapping("/simple")
public class Case1SimpleParameter {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 自动的映射基本类型，甚至包括一些特别的类型..比如BigDecimal
     *
     * 如果指定的参数没传的话？ ==> null
     *
     * e.g. http://localhost:8080/ordinary?username=admin&password=123&money=13.99
     *
     * 当然变量的名字要和传过来的名字对的上
     *
     * 其实你可以考虑一下，spring 是如何拿到你的字段名字叫username的呢 ? --> 反射是拿不到的
     *
     * @param username 用户名
     * @param password 密码
     * @return string
     */
    @RequestMapping("/ordinary")
    @ResponseBody
    public String ordinary(String username, String password, BigDecimal money) {
        logger.info("ordinary --- username : {}, password : {}, money: {}", username, password, money);
        return "I've got it";
    }

    /**
     * 相比于上一个例子。这个注解看起来好像多此一举呀
     * 但是如果你不传参数的话还会像上一个一样spring mvc传给你一个null吗？
     * 日志里面会输出一行: Required String parameter 'password' is not present
     * 浏览器显示 HTTP ERROR 400
     *
     * 呃..怎么感觉就是必须要这个参数的意思，好像也没有其他含义
     *
     * e.g. http://localhost:8080/request/params?username=admin&password=123
     *
     * @param username 用户名
     * @param password 密码
     * @return string
     */
    @RequestMapping("/request/params")
    @ResponseBody
    public String requestParams(@RequestParam String username,@RequestParam String password) {
        logger.info("params ---- username : {}, password : {}", username, password);
        return "I've got it";
    }

    /**
     * 点开RequestParam 这个注解，发现里面可以设置两个参数，可以设置是否必须，默认值值
     * 这么说来好像有那么一点点用处了.
     *
     * e.g. http://localhost:8080/request/params2
     *
     * @param username username
     * @return String
     */
    @RequestMapping("/request/params2")
    @ResponseBody
    public String requestParams2(@RequestParam(required = false, defaultValue = "admin") String username) {
        logger.info("params2 --- username: {}", username);
        return "I've got it";
    }

    /**
     *
     * 传统的url --->  http://hostname/path/to/controller?key=value&key=value&key=value
     * REST ---> http://hostname/path/to/controller/{value}/{value}
     * 一些网站会使用REST风格，因为url看起来更简洁，其实都ok。传统的风格是http协议的一部分。
     * 而REST风格需要特别的解析(很简单), Spring MVC就支持这样的做法
     * 我觉得这两没有必然的高下之分
     *
     * @see http://zh.wikipedia.org/wiki/REST
     *
     * 由于REST风格没有key这个概念，所以恩，自然不能不传，不传的话所有参数的位置都变了,所有没有默认值这样的说法
     *
     * e.g. http://localhost:8080/path/variable/admin/123
     *
     * @param username 用户名
     * @param password 密码
     * @return String
     */
    @RequestMapping("/path/variable/{username}/{password}")
    @ResponseBody
    public String pathVariable(@PathVariable String username, @PathVariable String password) {
        logger.info("path variable ---- username : {}, password : {}", username, password);
        return "I've got it";
    }

    //... 以上，了解以上，基本的参数的使用方法就ok
    //... 当然不排除在一些其他的情况下。前端会传一个array, matrix 等等http支持的语法。
    //... 学习这些东西意义不大，因为后来有了xml, json等等简单的数据格式，几乎不会再用到上面说到的方式
    //... 如果有前端要求你接受一个array 甚至是 matrix，你可以问他是不是上个世纪来的前端
    //... :P just a joking


    //... 以下内容就更不重要了，大概知道有这样一个东西就ok

    /**
     * 比如* 表示通配符...
     * 也可以写正则
     *
     * e.g. http://localhost:8080/path/express/user.jpg
     */
    @RequestMapping("/path/express/*.jpg")
    @ResponseBody
    public String express() {
        return "I've got it";
    }

    /**
     * 以及pathVariable也可以这样
     *
     * e.g. 自己拼去吧
     *
     * @param version version
     * @param extension extension
     * @return String
     */
    @RequestMapping("/spring-web/{symbolicName:[a-z-]}-{version:\\d\\.\\d\\.\\d}{extension:\\.[a-z]}")
    @ResponseBody
    public String whatTheFuck(@PathVariable String version,
                              @PathVariable String extension) {
        return "I've fucking got it";
    }

}
