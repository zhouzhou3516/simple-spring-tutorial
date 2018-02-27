package com.qunar.campus.spring.tutorial.proxy;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.proxy.base.Account;
import com.qunar.campus.spring.tutorial.proxy.base.AccountImpl;
import org.junit.Test;
import sun.reflect.Reflection;

import java.lang.reflect.Proxy;

/**
 * Description: ProxyTest
 *
 * 有兴趣可以看看动态代理的实现？
 * 没兴趣了解一下，直到有这样一个东西，可以像其他动态语言例如python,php中的magic call一样来代理一个类中的任何方法
 *
 * @see http://www.ibm.com/developerworks/cn/java/j-lo-proxy1/
 *
 * 0.  check
 * 1.  lock (cache table) --> 现在一般不会这么实现，可以用future表示计算过程
 * 2.  gen code (gen class file)
 * 3.  load and define a class file (native)
 *
 * 限制在于接口。Java中接口和类分得太开啦~
 *
 * @author yushen.ma
 * @version 2015-03-23 10:06
 */
@Synopsis(difficulty = Difficulty.NORMAL,
          name = "proxy",
          related = { Proxy.class, Reflection.class, ClassLoader.class} )
public class ProxyTest {

    @Test
    public void testStatic() {
        Account staticProxy = new StaticProxy(new AccountImpl());
        staticProxy.queryAccount();
        staticProxy.updateAccount();
    }

    @Test
    public void testJdkDynamic() throws Exception {
        Account proxy = (Account)new JDKProxy().getProxy(new AccountImpl());
        proxy.queryAccount();
        proxy.updateAccount();
    }
}
