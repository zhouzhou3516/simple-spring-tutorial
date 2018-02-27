package com.qunar.campus.spring.tutorial.bean.lifecycle.base;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description: BeanWithAnnotation
 *
 * @author yushen.ma
 * @version 2015-03-19 22:40
 */
@Synopsis(name="bean init callback", difficulty = Difficulty.EASY)
public class BeanWithAnnotation {


    private String username;

    private ExecutorService executorService;

    private UserService userService;

    @PostConstruct
    public void init() {
        //为什么不能把这段代码写在constructor里面呢？--constructor 的时候还没有初始化userService
        //思考：userService是在什么时候有值的
        username = userService.getCurUser();
        executorService = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(1));
    }

    @PreDestroy
    public void destroy() {
        this.executorService.shutdown();
    }


}
