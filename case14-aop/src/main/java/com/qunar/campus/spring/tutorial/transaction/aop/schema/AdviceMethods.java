package com.qunar.campus.spring.tutorial.transaction.aop.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: AdviceMethods
 *
 * @author yushen.ma
 * @version 2015-03-24 23:10
 */
public class AdviceMethods {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    public void preGreeting() {
        logger.info("here is pre greeting");
    }
}
