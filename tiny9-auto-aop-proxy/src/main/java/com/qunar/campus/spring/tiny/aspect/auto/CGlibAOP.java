package com.qunar.campus.spring.tiny.aspect.auto;

import com.qunar.campus.spring.tiny.aop.proxy.CGlibDynamicAopProxy;
import com.qunar.campus.spring.tiny.aspect.MethodMatcher;
import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * Description: CGlibAOP
 *
 * @author yushen.ma
 * @version 2015-03-22 17:04
 */
public class CGlibAOP<T> extends CGlibDynamicAopProxy<T> {

    private MethodMatcher methodMatcher;

    public CGlibAOP(T target, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        super(target, methodInterceptor);
        this.methodMatcher = methodMatcher;
    }

    @Override
    public Object intercept(final Object origin, final Method method,
                            final Object[] args, final MethodProxy methodProxy) throws Throwable {

        if (methodMatcher.matches(method, origin.getClass())) {
            return super.intercept(origin, method, args, methodProxy);
        }
        return method.invoke(target, args);//未命中aop匹配，则调用原来的方法
    }
}
