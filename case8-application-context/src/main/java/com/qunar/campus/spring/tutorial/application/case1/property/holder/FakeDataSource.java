package com.qunar.campus.spring.tutorial.application.case1.property.holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: FakeDataSource
 *
 * @author yushen.ma
 * @version 2015-03-17 23:05
 */
public class FakeDataSource {

    private String userName;

    private String password;

    final private Logger logger = LoggerFactory.getLogger(getClass());

    public FakeDataSource(String userName, String password) {
        this.userName = userName;
        this.password = password;
        logger.info("this is constructor");
    }

    /**
     * init 方法是在构造方法之前调用还是构造方法之后调用呢?
     */
    public void init() {
        //Class.forName("xxxDriver");
        //... connected to database
        logger.info("username: {} , password : {}", userName, password);
    }

    public String doSomething() {
        return "hello world";
    }
}
