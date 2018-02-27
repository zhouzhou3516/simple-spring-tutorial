package com.qunar.campus.spring.tutorial.homework2.controller.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qunar.campus.spring.tutorial.homework2.bean.APIResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

/**
 * 统一异常处理，将exception包装成APIResponse 类型
 * Created by liqingzhou on 17/8/2.
 */
@Component
public class ResponseBodyExceptionResolver implements HandlerExceptionResolver {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    static Logger logger = LoggerFactory
            .getLogger(ResponseBodyExceptionResolver.class.getSimpleName());

    private static void response(Object value, HttpServletResponse response) {
        try {
            response.setStatus(HttpStatus.OK.value()); //设置状态码
            response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType
            response.setCharacterEncoding("UTF-8"); //避免乱码
            objectMapper.writeValue(response.getWriter(), value);
        } catch (IOException e) {
            logger.warn("response write error", e);
        }

    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object _handler, Exception ex) {

        HandlerMethod handler = (HandlerMethod) _handler;
        if (handler == null) {
            // like 'GET' not supported
            return null;
        }
        Method method = handler.getMethod();

        if (method.isAnnotationPresent(ResponseBody.class)) {

            if (ex instanceof IllegalArgumentException) {

                response(APIResponse.failed(ex.getMessage()), response);
            }
            // can deal other type Ex
            else {
                response(APIResponse.failed(ex.getClass().getSimpleName()), response);
                logger.warn("error ::",ex);
            }
            // skip other resolver and view render
            return ModelAndViewResolver.UNRESOLVED;
        }

        return null;

    }
}
