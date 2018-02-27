package com.qunar.campus.spring.tutorial.bean.lifecycle.base;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;

import java.util.concurrent.*;

/**
 * Description: BeanWithInitDefinition
 *
 * @author yushen.ma
 * @version 2015-03-19 22:28
 */
@Synopsis(name="bean init callback", difficulty = Difficulty.EASY)
public class BeanWithInitDefinition {

    private String username;

    private ExecutorService executorService;

    private UserService userService;

    public void init() {
        //为什么不能把这段代码写在constructor里面呢？
        //思考：userService是在什么时候有值的
        username = userService.getCurUser();
        executorService = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(1));
    }

    public void destroy() {
        executorService.shutdown();
    }

}
