package com.qunar.campus.spring.tutorial.framework;

import com.qunar.campus.spring.tutorial.framework.service.UserService;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: GetSpringContext
 *
 * @author yushen.ma
 * @version 2015-04-07 00:07
 */
public class GetSpringContext extends HttpServlet {

    private static final long serialVersionUID = 1157860865956723542L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext spring
                = (ApplicationContext)req.getSession().getServletContext().getAttribute("spring");
        UserService bean = spring.getBean(UserService.class);
        resp.getWriter().println(bean.getUserName());
    }
}
