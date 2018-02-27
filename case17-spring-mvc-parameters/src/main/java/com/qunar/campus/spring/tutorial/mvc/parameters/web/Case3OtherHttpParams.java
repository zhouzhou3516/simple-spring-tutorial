package com.qunar.campus.spring.tutorial.mvc.parameters.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description: Case3OtherHttpParams
 *
 * 除了simple params以后一些其他的参数的取发。其中包括header, cookie, session
 *
 * @author yushen.ma
 * @version 2015-03-28 17:31
 */
@Controller
@RequestMapping("/other/params")
public class Case3OtherHttpParams {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取http request对象
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping("/request")
    @ResponseBody
    public String request(HttpServletRequest request) {
        logger.info("{}", request);
        return "I've got httpRequest";
    }

    /**
     * 获取http response对象
     * @param response HttpServletResponse
     * @return String
     */
    @RequestMapping("/response")
    @ResponseBody
    public String request(HttpServletResponse response)  {
        logger.info("{}", response);
        return "I've got it";
    }

    /**
     * 获取session
     * @param httpSession HttpSession
     * @return String
     */
    @RequestMapping("/session")
    @ResponseBody
    public String session(HttpSession httpSession) {
        logger.info("{}", httpSession);
        return "I've got session";
    }

    /**
     * 获取cookie里面的值
     * @param username a value in cookie
     * @return String
     */
    @RequestMapping("/cookie")
    @ResponseBody
    public String cookie(@CookieValue(value = "username") String username) {
        logger.info("{}", username);
        return "I've got username in cookie";
    }

    /**
     * 设置cookie
     * @param response response
     * @return String
     */
    @RequestMapping("/set/cookie")
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        logger.info("{}", response);
        response.addCookie(new Cookie("username","admin"));
        return "I've set ur username in cookie";
    }

    /**
     * 获取Http请求头部的一些参数
     * @param contentType the content type in header
     * @return String
     */
    @RequestMapping("/header")
    @ResponseBody
    public String header(@RequestHeader("content-type") String contentType) {
        logger.info("{}", contentType);
        return "I've got the contentType";
    }
}
