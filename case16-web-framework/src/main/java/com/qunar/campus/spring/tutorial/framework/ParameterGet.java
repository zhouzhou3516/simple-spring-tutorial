package com.qunar.campus.spring.tutorial.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: com.qunar.campus.spring.tutorial.framework.ParameterGet
 *
 * @author yushen.ma
 * @version 2015-04-06 21:44
 */
public class ParameterGet extends HttpServlet {

    private static final long serialVersionUID = 5194960575166281775L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //...
        super.doGet(req, resp);
    }
}
