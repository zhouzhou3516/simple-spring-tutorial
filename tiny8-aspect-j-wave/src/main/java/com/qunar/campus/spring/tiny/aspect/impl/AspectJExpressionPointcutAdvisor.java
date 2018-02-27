package com.qunar.campus.spring.tiny.aspect.impl;

import com.qunar.campus.spring.tiny.aspect.Pointcut;
import com.qunar.campus.spring.tiny.aspect.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 该对象就能够表示一次AOP
 *
 * @author yushen.ma
 * @version 2015-03-20 13:55
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    private Advice advice;

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

	@Override
	public Advice getAdvice() {
		return advice;
	}

    @Override
	public Pointcut getPointcut() {
		return pointcut;
	}
}
