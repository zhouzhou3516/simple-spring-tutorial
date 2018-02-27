package com.qunar.campus.spring.tutorial.mvc.resolver.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: Case1Redirect
 *
 * 请务必搞清楚 forward和redirect
 * @see http://stackoverflow.com/questions/6068891/difference-between-jsp-forward-and-redirect
 *
 * 也务必搞清楚他们的使用场景
 *
 * @author yushen.ma
 * @version 2015-03-29 16:05
 */
@Controller
@RequestMapping("/redirect")
public class Case1Redirect {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 访问该方法
     * http://localhost:8080/redirect/forward
     * 服务器内部将会接下来调用/redirect/target方法
     *
     * 这其实是从jsp开始的，为了在servlet和jsp之间共享RequestContext，
     * 以便分离逻辑与视图
     *
     * 在servlet中设置一些值到当前上下文中
     * 然后在jsp中取出后直接渲染页面
     */
    @RequestMapping("/forward")
    public String forward(HttpServletRequest request) {
        logger.info("I'm in the forward method");
        request.setAttribute("data", "I'm data");
        return "forward:/redirect/target";
    }

    /**
     * 访问该方法后，服务器返回302
     * 和Location,
     * 浏览器自动根据302和location跳转到目标地址
     * 常常用于登录。验证...xxx
     *
     * http://localhost:8080/redirect/target
     * @return String
     */
    @RequestMapping("/redirect")
    public String direct() {
        logger.info("I'm in the redirect method");
        return "redirect:/redirect/target";
    }

    /**
     * 这是forward和redirect的目标方法
     * @return String
     */
    @RequestMapping("/target")
    @ResponseBody
    public String targetMethod(HttpServletRequest request) {
        logger.info("I'm in the target method");
        return "I'm in the target method with \"" + request.getAttribute("data") + "\"";
    }

}
