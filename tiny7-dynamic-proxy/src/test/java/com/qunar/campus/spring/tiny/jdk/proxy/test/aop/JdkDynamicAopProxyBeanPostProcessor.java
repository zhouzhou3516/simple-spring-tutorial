package com.qunar.campus.spring.tiny.jdk.proxy.test.aop;

import com.qunar.campus.spring.tiny.aop.proxy.JdkDynamicAopProxy;
import com.qunar.campus.spring.tiny.context.BeanPostProcessor;
import com.qunar.campus.spring.tiny.jdk.proxy.UserService;

/**
 * 利用BeanPostProcessor加入动态代理
 *
 * @author yushen.ma
 * @version 2015-03-20 09:33
 */
public class JdkDynamicAopProxyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        //使用JDK代理，我们只能监听某个interface
        //但是在多数场景下面，其实很多类是没有接口的
        if (!(bean instanceof UserService)) {
            return bean;
        }
        return new JdkDynamicAopProxy(bean, new TimerInterceptor()).getProxy(UserService.class);
    }
}
