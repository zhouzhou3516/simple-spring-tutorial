package com.qunar.campus.spring.tiny.aop.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Assert;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于jdk的动态代理
 *
 * 我偷懒地把生成代理类的工厂和代理类写在了一起
 *
 *      Spring AOP uses either JDK dynamic proxies or CGLIB to
 * create the proxy for a given target object. (JDK dynamic
 * proxies are preferred whenever you have a choice).
 *      If the target object to be proxied implements at least one
 * interface then a JDK dynamic proxy will be used. All of the
 * interfaces implemented by the target type will be proxied.
 * If the target object does not implement any interfaces then
 * a CGLIB proxy will be created.
 *  If you want to force the use of CGLIB proxying
 *  (for example, to proxy every method defined for the target object,
 *  not just those implemented by its interfaces) you can do so.
 *
 *  However, there are some issues to consider:
      1. final methods cannot be advised, as they cannot be overridden.
      2. As of Spring 3.2, it is no longer necessary to add CGLIB to your project classpath,
         as CGLIB classes are repackaged under org.springframework and included directly in
         the spring-core JAR. This means that CGLIB-based proxy support just works in the
         same way that JDK dynamic proxies always have.
      3. The constructor of your proxied object will be called twice.
         This is a natural consequence of the CGLIB proxy model whereby a subclass is
         generated for each proxied object. For each proxied instance,
         two objects are created: the actual proxied object and an instance
         of the subclass that implements the advice. This behavior is not
         exhibited when using JDK proxies. Usually, calling the constructor of the proxied type twice,
          is not an issue, as there are usually only assignments taking place and no real logic is implemented in the constructor.

      To force the use of CGLIB proxies set the value of the proxy-target-class attribute of the <aop> element to true
      <aop:config proxy-target-class="true"/>
      <aop:aspectj-autoproxy proxy-target-class="true"/> ---->这个标签的意思是，扫描AspectJ提供的注解：@Aspect @Advice @PointCut 等等
      <tx:annotation-driven proxy-target-class="true"/>  启动 @Transactional 注解
 *
 * @author yushen.ma
 * @version 2015-03-20 13:55
 */
public class JdkDynamicAopProxy<T> implements InvocationHandler {

    private final T target;

    private final MethodInterceptor methodInterceptor;

    public JdkDynamicAopProxy(T target, MethodInterceptor methodInterceptor) {
        this.target = target;
        this.methodInterceptor = methodInterceptor;
    }

    @SuppressWarnings("unchecked")
	public T getProxy(Class<T> interFace) {
        Assert.assertTrue(interFace.isInterface());
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { interFace }, this);
	}

	@Override
	public Object invoke(Object origin, final Method method, final Object[] args) throws Throwable {
        //我这样写你会不会看不懂呢..
        //主要是为了描述method interceptor,所以使用了aop联盟定义的，MethodInterceptor
        //你只要理解interceptor就可以看明白
		return methodInterceptor.invoke(new MethodInvocation() {
            @Override
            public Method getMethod() {
                return method;
            }

            @Override
            public Object[] getArguments() {
                return args;
            }

            @Override
            public Object proceed() throws Throwable {
                return method.invoke(target, args);
            }

            @Override
            public Object getThis() {
                return target;
            }

            @Override
            public AccessibleObject getStaticPart() {
                return method;
            }
        });
	}

}
