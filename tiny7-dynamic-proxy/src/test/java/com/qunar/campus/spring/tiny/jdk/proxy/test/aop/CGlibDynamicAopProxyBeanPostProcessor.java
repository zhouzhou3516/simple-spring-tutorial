package com.qunar.campus.spring.tiny.jdk.proxy.test.aop;

import com.qunar.campus.spring.tiny.aop.proxy.CGlibDynamicAopProxy;
import com.qunar.campus.spring.tiny.context.BeanPostProcessor;

/**
 * Description: CGlibDynamicAopProxyBeanPostProcessor
 *
 * @author yushen.ma
 * @version 2015-03-21 19:58
 */
public class CGlibDynamicAopProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //我们可以代理任何类
        return new CGlibDynamicAopProxy(bean, new TimerInterceptor()).getProxy();
    }
}
