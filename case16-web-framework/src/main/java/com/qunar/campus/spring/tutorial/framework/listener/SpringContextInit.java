package com.qunar.campus.spring.tutorial.framework.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Description: SpringContextInit
 *
 * 通过这样的办法，我们就可以在整个web应用程序中设置共享这个spring-application-context
 *
 * @author yushen.ma
 * @version 2015-04-06 23:57
 */
public class SpringContextInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        sce.getServletContext().setAttribute("spring", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }


}
