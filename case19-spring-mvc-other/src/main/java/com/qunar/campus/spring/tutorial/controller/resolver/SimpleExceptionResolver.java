package com.qunar.campus.spring.tutorial.controller.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: SimpleExceptionResolver
 *
 * 一个支持JSON的异常处理的解析器
 * 将dispatch-servlet里面的异常处理器类替换成这个即可
 *
 * Tips 这只是一个简单的示例。在真实的线上环境中不要轻易使用。
 *
 * @author yushen.ma
 * @version 2015-04-04 18:25
 */
public class SimpleExceptionResolver extends SimpleMappingExceptionResolver {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Object _handler, Exception ex) {
        if (!(_handler instanceof HandlerMethod)) {
            return super.doResolveException(request, response, hashCode(), ex);
        }
        HandlerMethod handler = (HandlerMethod) _handler;
        // json view
        if (handler.getMethod().isAnnotationPresent(ResponseBody.class)) {
            try {
                response.getOutputStream().write(objectMapper.writeValueAsString(
                        JsonResponse.failed(ex.getMessage())).getBytes("UTF-8"));
            } catch (IOException e) {
                return super.doResolveException(request, response, hashCode(), ex);
            }
            // skip other resolver and view render
            return ModelAndViewResolver.UNRESOLVED;
        }

        return super.doResolveException(request, response, handler, ex);
    }

    /**
     * 这里构造一个简易的JSON返回对象，
     * 只是为了说明
     * @param <T>
     */
    public static class JsonResponse<T> {

        /** 表示某次请求执行的状态，0表示失败，其他表示成功 */
        private int status;

        /** 表示请求返回的数据 */
        private T data;

        /** 表示请求返回的提示信息（用户展示给用户）， 通常在错误的情况下使用*/
        private String message;

        public JsonResponse() {
        }

        public JsonResponse(int status, T data, String message) {
            this.status = status;
            this.data = data;
            this.message = message;
        }

        @SuppressWarnings("unchecked")
        public static JsonResponse failed(String errorMsg) {
            return new JsonResponse(0, null, errorMsg);
        }

        public static <T> JsonResponse<T> success(T data) {
            return new JsonResponse<T>(0, data, null);
        }
    }
}
