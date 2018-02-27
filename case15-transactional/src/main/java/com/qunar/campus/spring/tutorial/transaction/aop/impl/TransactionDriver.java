package com.qunar.campus.spring.tutorial.transaction.aop.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * Description: TransactionDriver
 *
 * @author yushen.ma
 * @version 2015-03-26 15:43
 */
@Aspect
public class TransactionDriver {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    private DataSourceTransactionManager transactionManager;

    public TransactionDriver(DataSourceTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Around("@annotation(org.springframework.transaction.annotation.Transactional)")
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        TransactionStatus transaction =
                this.transactionManager.getTransaction(new DefaultTransactionDefinition());

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable t) {
            logger.info("manager catch exception {}, we'll rollback", t);
            this.transactionManager.rollback(transaction);
            throw t;
        }
        logger.info("manager get it, we'll commit");
        this.transactionManager.commit(transaction);
        return result;
    }
}
