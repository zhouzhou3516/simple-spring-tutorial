package com.qunar.campus.spring.tutorial.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Description: com.qunar.campus.spring.tutorial.framework.HelloServlet
 *
 * this is a simple hello servlet
 *
 * @author yushen.ma
 * @version 2015-04-06 20:15
 */
public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = -4439637135600892326L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.print("it works");

    }

}
