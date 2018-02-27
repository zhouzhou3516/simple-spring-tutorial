package com.qunar.campus.spring.tutorial.proxy.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: AccountImpl
 *
 * @author yushen.ma
 * @version 2015-03-23 10:01
 */
public class AccountImpl implements Account {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void queryAccount() {
        logger.info("query account");
    }

    @Override
    public void updateAccount() {
        logger.info("update account");
    }
}
