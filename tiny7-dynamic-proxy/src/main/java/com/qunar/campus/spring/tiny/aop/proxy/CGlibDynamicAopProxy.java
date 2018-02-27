package com.qunar.campus.spring.tiny.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * Description: CGlibDynamicAopProxy
 *
 * 基于CGlib实现的
 *
 * 注意为了兼容AOP联盟定义的MethodInterceptor的概念，这里被迫引用了两个methodInterceptor
 *
 * @author yushen.ma
 * @version 2015-03-21 19:07
 */
public class CGlibDynamicAopProxy<T> implements net.sf.cglib.proxy.MethodInterceptor {

    protected final T target;

    protected final MethodInterceptor methodInterceptor;

    public CGlibDynamicAopProxy(T target, MethodInterceptor methodInterceptor) {
        this.target = target;
        this.methodInterceptor = methodInterceptor;
    }

    @SuppressWarnings("unchecked")
    public T getProxy() {//这里就不用传一个interface进来，因为无论传什么类进来都可以代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(final Object origin, final Method method,
                            final Object[] args, final MethodProxy methodProxy) throws Throwable {
        return methodInterceptor.invoke(new MethodInvocation() {
            @Override
            public Method getMethod() {
                return method;
            }

            @Override
            public Object[] getArguments() {
                return args;
            }

            //其实我们只是简单地用到了这个方法而已哈
            @Override
            public Object proceed() throws Throwable {
                return methodProxy.invokeSuper(origin, args);
            }

            @Override
            public Object getThis() {
                return origin;
            }

            @Override
            public AccessibleObject getStaticPart() {
                return method;
            }
        });
    }
}
