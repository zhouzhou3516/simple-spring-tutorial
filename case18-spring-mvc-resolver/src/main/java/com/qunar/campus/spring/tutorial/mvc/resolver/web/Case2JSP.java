package com.qunar.campus.spring.tutorial.mvc.resolver.web;

import com.qunar.campus.spring.tutorial.mvc.resolver.web.bean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description: Case2JSP
 *
 * 本case讲述如何返回一个jsp页面
 *
 * @see http://my.oschina.net/HeliosFly/blog/221392
 *
 * @author yushen.ma
 * @version 2015-03-29 19:17
 */
@Controller
@RequestMapping("/jsp")
public class Case2JSP {

    /**
     * 当我们直接访问这个
     * http://localhost:8080/jsp/simple
     * 会出现404
     * 日志里面会打印
     * Did not find handler method for [/WEB-INF/jsp/simple/case_one.jsp]
     *
     * 原因是我们在web.xml中将/* 的请求全部交给了DispatchServlet
     * @see org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler
     *
     * 我们只需要在dispatch_servlet.xml中加上一句
     * <mvc:default-servlet-handler/>
     *
     * 就会设置该Handler为最低级别的handler
     * 在找不到请求映射的控制器的时候，就会调用该处理器。可是jsp页面没有解析

       BTW web.xml的匹配规则： 1. 完全匹配， 2. 路径模糊匹配（/*,/asd/*）  3. 后缀模糊匹配(*.img) 4. /
       (通常来说 tomcat之类的服务器会内置一些mapping, 例如 *.jsp, / defaultServlet)

    [ ]	无线payCheck需要兼容老的版本

    1. 当我们使用"/*"来匹配的时候，只有完全匹配，或者比这个更精确的匹配才可以映射到。
       这样一来，我们的jsp页面完全不能被转发，因为所有请求都打到了Spring 的DispatchServlet
       如此一来我们只需要配置<default-servlet-handler/>就可以代理到静态资源(jsp不会被解析)

       2. 在某些情况下，你会看见有的spring配置里面设置的DispatchServlet里面的mapping-url是/
       在没有其他的匹配的情况下，这个还好。并且能够成功匹配到*.jsp,因为*.jsp的优先级更高
       同理/覆盖了静态资源
       需要配置： <mvc:resources mapping="/file/**" location="/file/"/>

     * 3. 当然也可以使用带有后缀的匹配的方式进入spring-dispatchServlet,如sample1中的例子
     * 或者使用/xxx/* 来mapping spring defaultServlet
     *
     * 所以以上三种自选吧。可以自行测试一下上述的三种情况
     *
     * @param model 数据模型
     * @return String
     */
    @RequestMapping("/simple")
    public String simple(Model model) {
        model.addAttribute("username", "admin");
        return "simple/case_first";
    }

    /**
     * 还有一种类似的
     * @param modelAndView modelAndView
     * @return ModelAndView
     */
    @RequestMapping("/model/view")
    public ModelAndView modelAndView(ModelAndView modelAndView) {
        modelAndView.addObject("username", "admin");
        modelAndView.setViewName("simple/case_first");
        return modelAndView;
    }

    /**
     * model attribute是一个很古老的技术。(i think so)
     * 基本不会使用，它大致的想法是自动地将一些对象填充到HttpServletRequest中
     * 包括session attribute同理
     * --> 不用看,知道有这么个东西就ok
     * @param userInfo userInfo
     * @return String of view name
     */
    @RequestMapping("/model/attribute")
    public String modelAttribute(@ModelAttribute UserInfo userInfo) {
        userInfo.setPassword("****");
        return "simple/case_second";
    }

    // --------
    // 在sample1-simple-web-app中使用了 VelocityLayoutViewResolver
    // 其使用了一种叫做Velocity的模板引擎技术
    // 自行百度什么是模板引擎，其作用是什么？
    // Tips: 不要花太多的时间在这种“模板技术”上面

}
