package com.qunar.campus.spring.tutorial.proxy;

import com.qunar.campus.spring.tutorial.proxy.base.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: StaticProxy
 *
 * 静态代理
 *
 * @author yushen.ma
 * @version 2015-03-23 10:00
 */
public class StaticProxy implements Account {

    private Account account;

    final private Logger logger = LoggerFactory.getLogger(getClass());

    public StaticProxy(Account account) {
        this.account = account;
    }

    @Override
    public void queryAccount() {
        // 1. 事务处理前
        logger.info("before query");

        // 2. actual method
        account.queryAccount();

        // 3. 事务处理后
        logger.info("after query");
    }

    @Override
    public void updateAccount() {
        // 1. 事务处理前
        logger.info("before update");

        // 2. actual method
        account.updateAccount();

        // 3. 事务处理后
        logger.info("after update");
    }
}
