package com.qunar.campus.spring.tutorial.transaction.aop.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description: Waiter
 *
 * @author yushen.ma
 * @version 2015-03-23 22:24
 */
@Component
public class Waiter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void greetTo() {
        logger.info("naive waiter:greet to u");
    }

    public void serveTo() {
        logger.info("naive waiter: serve to u");
    }

    public void iHaveSomeParams(int i, String user) {
        logger.info("I'v got some params {}, {}", i, user);
    }
}
