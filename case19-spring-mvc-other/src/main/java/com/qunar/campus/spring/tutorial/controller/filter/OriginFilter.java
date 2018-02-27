package com.qunar.campus.spring.tutorial.controller.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description: OriginFilter
 *
 * 如何在filter中拿到spring容器
 *
 * @author yushen.ma
 * @version 2015-03-31 10:18
 */
public class OriginFilter implements Filter {

    private ServletContext servletContext;

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //可以看看这个方法是如何实现的
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        //他还有个父级context
        ApplicationContext parent = webApplicationContext.getParent();
        //以下就可以获取bean对象啦
        //但是父子容器的关系是..？
    }

    @Override
    public void destroy() {
        // do nothing
    }
}
