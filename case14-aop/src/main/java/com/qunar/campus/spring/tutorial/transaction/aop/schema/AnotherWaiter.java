package com.qunar.campus.spring.tutorial.transaction.aop.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description: AnotherWatier
 *
 * @author yushen.ma
 * @version 2015-03-24 23:12
 */
public class AnotherWaiter {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    public void greeting() {
        logger.info("hello i'm another waiter");
    }
}
