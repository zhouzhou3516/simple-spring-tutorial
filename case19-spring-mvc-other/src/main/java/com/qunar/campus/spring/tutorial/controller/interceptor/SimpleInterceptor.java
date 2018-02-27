package com.qunar.campus.spring.tutorial.controller.interceptor;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.ANotUsefulAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: SimpleInterceptor
 *
 * FAQ 1.spring MVC中的HandlerInterceptor与Servlet.Filter的差异是什么?

  HandlerInterceptor is basically similar to a Servlet 2.3 Filter,
  but in contrast to the latter it just allows custom pre-processing
  with the option of prohibiting the execution of the handler itself,
  and custom post-processing. Filters are more powerful, for example
  they allow for exchanging the request and response objects that are
  handed down the chain. Note that a filter gets configured in web.xml,
  a HandlerInterceptor in the application context.

  As a basic guideline, fine-grained handler-related preprocessing tasks
  are candidates for HandlerInterceptor implementations, especially
  factored-out common handler code and authorization checks.
  On the other hand, a Filter is well-suited for request content and
  view content handling, like multipart forms and GZIP compression.
  This typically shows when one needs to map the filter to certain
  content types (e.g. images), or to all requests.

  @see http://docs.spring.io/spring/docs/3.0.x/javadoc-api/org/springframework/web/servlet/HandlerInterceptor.html
  @see http://stackoverflow.com/questions/8000844/spring-handlerinterceptor-vs-servlet-filters

 *
 * 另外有个重要的作用就是能够拿到具体的Controller的方法
 *
 * 写完这个类之后，还需要在外面配置一下
 *
 * @author yushen.ma
 * @version 2015-04-04 16:13
 */
@Component
public class SimpleInterceptor implements HandlerInterceptor {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * ThreadLocal 是一个之后会常用用到的工具
     *
     * FAQ 2. when and how should i use a thread local variable
     * @see http://stackoverflow.com/questions/817856/when-and-how-should-i-use-a-threadlocal-variable
     *
     * BTW 我猜你很容易就可以写一个他的实现出来。不过你也可以看看它到底是如何实现的
     */
    ThreadLocal<Long> benchMark = new ThreadLocal<Long>();

    /**
     * 调用controller方法之前的回调
     * @param request request
     * @param response response
     * @param _handler _handler
     * @return boolean
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object _handler) throws Exception {
        // clean benchMark
        benchMark.remove();

        /**
         * case 1 : 注意handler有很多种类型
         * handlerMethod 处理spring controller
         * DefaultServletHttpRequestHandler 处理静态资源
         * ...
         */
        if (!(_handler instanceof HandlerMethod)) {
            return true;
        }

        /**
         * case 2: 可以获取当前处理的方法名称
         */
        HandlerMethod handler = (HandlerMethod) _handler;
        Method method = handler.getMethod();
        logger.info("interceptor method -- {}", method);
        MethodParameter[] methodParameters = handler.getMethodParameters();
        logger.info("interceptor parameters -- {}", methodParameters);

        /**
         * case 3: annotation driven
         * 联想一下之前我们学过的注解，其实这个时候我们能够拿到具体的处理类。并且可以取得这些方法上面的注解
         * 于是我们就可以对注解进行"驱动" ---> 脑洞大开呀
         */
        boolean annotationPresent = method.isAnnotationPresent(ANotUsefulAnnotation.class);
        if (annotationPresent) {
            // do some magic things
        }

        /**
         * case 4: ThreadLocal
         */
        benchMark.set(System.currentTimeMillis());
        return true;
    }

    /**
     * 调用controller方法完了之后的回调
     * @param request request
     * @param response response
     * @param handler handler
     * @param modelAndView modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if ( !(handler instanceof HandlerMethod)) {
            return;
        }
        // calculate bench mark
        logger.info("method process cast : {}", System.currentTimeMillis() - benchMark.get());
        // reset
        benchMark.remove();
    }

    /**
     * callback after completion of request processing, that is, after rendering
     * the view. Will be called on any outcome of handler execution, thus allows
     * for proper resource cleanup.
     *
     * 只有当请求确实“完成了”才会调用到这里来
     *
     * @param request request
     * @param response response
     * @param handler handler
     * @param ex exception
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
