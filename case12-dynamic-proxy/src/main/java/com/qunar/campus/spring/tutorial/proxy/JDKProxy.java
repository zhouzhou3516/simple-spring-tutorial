package com.qunar.campus.spring.tutorial.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description: JDKProxy
 *
 * @author yushen.ma
 * @version 2015-03-23 10:08
 */
public class JDKProxy implements InvocationHandler {

    private Object target;

    final private Logger logger = LoggerFactory.getLogger(getClass());

    public Object getProxy(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1. before something
        logger.info("before something");
        // 2. actual method call
        try {
            return method.invoke(this.target, args);
        } finally {
            // 3. after something
            logger.info("after something");
        }
    }
}
