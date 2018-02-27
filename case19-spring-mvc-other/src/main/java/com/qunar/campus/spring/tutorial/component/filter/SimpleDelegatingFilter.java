package com.qunar.campus.spring.tutorial.component.filter;

import com.qunar.campus.spring.tutorial.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: DelegatingFilter
 *
 * org.springframework.web.filter中有一个特殊的类——DelegatingFilterProxy，
 * 该类其实并不能说是一个过滤器，它的原型是FilterToBeanProxy，
 * 即将Filter作为spring的bean，由spring来管理。
 *
 * 1. DelegatingFilter 必须要继承servlet.filter
 *
 * 2. 在web.xml配置spring filter代理
 *
 * 3. 就可以任意使用spring标签
 *
 * 4. 可以yy一下它的实现
 *
 *
 * FAQ 1.为什么要把filter放在component下面
 *
 * 1. 我们的配置文件有两个，分别是config.xml 和 dispatch-servlet.xml。
 * 其中该bean在config.xml中被加载
 * 2. 这两个配置文件分别被listener, 和dispatcherServlet加载
 * 3. web容器初始化的顺序是：listener > filter > servlet(如果没有配置load-on-startup 将会延迟加载)
 * 4. 所以，如果，我们把该bean放在dispatcher-servlet.xml中加载, 势必filter先加载，并且在容器中无法找到
 * 这个bean,会报错退出。
 * 5. 所以代理filter所引用的对象务必放在listener的spring-application-context中加载
 *
 * @author yushen.ma
 * @version 2015-04-02 00:35
 */
@Component
public class SimpleDelegatingFilter implements Filter {

    @Resource
    UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest _request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) _request;
        if (request.getRequestURI().startsWith("/user/login")) {
            chain.doFilter(_request, response);
            return; // remember this fucking return statement
        }
        HttpSession session = request.getSession();
        String username = String.valueOf(session.getAttribute("username"));
        if (StringUtils.isNotBlank(username)) {
            chain.doFilter(_request, response);
            return;
        }
        PrintWriter writer = response.getWriter();
        writer.println("access deny");
    }

    @Override
    public void destroy() {
        // do nothing
    }

}
