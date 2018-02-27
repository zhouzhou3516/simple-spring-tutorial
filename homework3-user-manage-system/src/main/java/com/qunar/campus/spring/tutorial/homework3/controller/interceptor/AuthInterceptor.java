package com.qunar.campus.spring.tutorial.homework3.controller.interceptor;

import com.qunar.campus.spring.tutorial.homework3.bean.security.Subject;
import com.qunar.campus.spring.tutorial.homework3.util.PublicConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by liqingzhou on 17/8/4.
 */
@EnableWebMvc
public class AuthInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        Subject subject = (Subject) request.getSession()
                .getAttribute(PublicConstant.SESSION_LOGIN_SUBJECT);

        if (subject == null) {
            logger.info("未登录");
            response.sendRedirect("login");
            return false;
        } else if (StringUtils.equals("user", subject.getRole())) {
            logger.info("subject {}", subject);
            if (request.getServletPath().startsWith("/user/welcome")) {
                return true;
            }
            response.sendRedirect("welcome");
            return false;
        } else {
            logger.info("subject {}", subject);
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {

    }
}
