package com.qunar.campus.spring.tiny.aspect.auto;

import com.google.common.base.Preconditions;
import com.qunar.campus.spring.tiny.aspect.Pointcut;
import com.qunar.campus.spring.tiny.aspect.PointcutAdvisor;
import com.qunar.campus.spring.tiny.aspect.impl.AspectJExpressionPointcut;
import com.qunar.campus.spring.tiny.context.BeanPostProcessor;
import com.qunar.campus.spring.tiny.context.InitializingBean;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.apache.commons.lang3.StringUtils;

/**
 * Description: AOPAdvisor
 *
 * @author yushen.ma
 * @version 2015-03-22 16:41
 */
public class AOPAdvisor implements BeanPostProcessor, PointcutAdvisor, InitializingBean {

    private String expression;

    private MethodInterceptor methodInterceptor;

    private AspectJExpressionPointcut aspectJExpressionPointcut;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (! this.getPointcut().getClassFilter().matches(bean.getClass())) {
            return bean;
        }
        //我们就只是支持一下 MethodInterceptor 就可以啦,其他的advice其实类似
        return new CGlibAOP(bean,
                (MethodInterceptor) this.getAdvice(),
                getPointcut().getMethodMatcher())
                .getProxy();
    }

    @Override
    public Pointcut getPointcut() {
        return aspectJExpressionPointcut;
    }

    @Override
    public Advice getAdvice() {
        return methodInterceptor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(expression));
        aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression(expression);
    }
}
