package com.qunar.campus.spring.tutorial.bean.lifecycle.base;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: BeanWithCallBackInterface
 *
 * 即使有了PostConstruct等等注解，但是还是不能完全替代CallBack的作用
 *
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.context.ApplicationEventPublisherAware
 * @see org.springframework.beans.factory.BeanClassLoaderAware
 * @see org.springframework.beans.factory.BeanFactoryAware
 * @see org.springframework.beans.factory.BeanNameAware
 *
 * @author yushen.ma
 * @version 2015-03-19 22:34
 */
@Synopsis(name="bean callbacks", difficulty = Difficulty.EASY)
public class BeanWithCallBackInterface implements InitializingBean,DisposableBean {

    private String username;

    private UserService userService;
    private ThreadPoolExecutor executorService;

    @Override
    public void afterPropertiesSet() throws Exception {
       //为什么不能把这段代码写在constructor里面呢？
        //思考：userService是在什么时候有值的
        username = userService.getCurUser();
        executorService = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(1));
    }

    @Override
    public void destroy() throws Exception {
        executorService.shutdown();
    }
}
