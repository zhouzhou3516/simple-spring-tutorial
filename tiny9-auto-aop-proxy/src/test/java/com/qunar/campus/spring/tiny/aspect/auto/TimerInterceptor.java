package com.qunar.campus.spring.tiny.aspect.auto;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 记录每个方法调用的时间
 *
 * 注意：MethodInterceptor是advice的一个子类
 *
 * @author yushen.ma
 * @version 2015-03-20 09:33
 */
public class TimerInterceptor implements MethodInterceptor {

    final private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.info("Invocation of {} start", invocation.getMethod().getName());
		Object proceed = invocation.proceed();
        logger.info("Invocation of {} end", invocation.getMethod().getName());
		return proceed;
	}
}
