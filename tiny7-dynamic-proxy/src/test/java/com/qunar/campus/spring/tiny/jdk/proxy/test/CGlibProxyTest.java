package com.qunar.campus.spring.tiny.jdk.proxy.test;

import com.qunar.campus.spring.tiny.context.ApplicationContext;
import com.qunar.campus.spring.tiny.context.XmlApplicationContext;
import com.qunar.campus.spring.tiny.jdk.proxy.PriceService;
import com.qunar.campus.spring.tiny.jdk.proxy.UserService;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Test;

/**
 * Description: CGlibProxyTest
 *
 * 其实到了这里，你应该大致能够明白@Transactional标签是如何实现的
 * 其实，spring-tx包中注册了一个BeanPostProcessor，在bean实例化的时候，
 * 他会检查每个bean的每个方法，如果有@Transactional标签，那么他会生成一个代理
 * 他的method interceptor就像这样
 *
 * 1. 开启事务
 *
 * try {
 *     2. 调用原来的方法
 * } catch {
 *     3. 事务回滚
 * }
 *
 * 4. 事务提交
 *
 * 当然在这个过程中，他其实还做了一个事务传播级别的事情。
 * 例如a方法带有@Transactional标签，b方法调用a方法，b也带有这个标签
 * 那么当a执行的时候,会不会在这个b的事务的基础上再开一个事务呢？
 *
 * @see org.springframework.transaction.annotation.Propagation
 *
 * 其中运用的就是ThreadLocal保存一个事务上下文，然后根据你配置的传播属性，在代理方法中再决定是否需要新开事务
 * 也可以从ThreadLocal中拿到事务引用，以便a方法中的事务回滚等
 *
 * @author yushen.ma
 * @version 2015-03-21 20:01
 */
@Synopsis(name="CGlib proxy", difficulty = Difficulty.HARD)
public class CGlibProxyTest {

    @Test
    public void testXMLFactory() {

        // 1.读取配置
        ApplicationContext applicationContext = new XmlApplicationContext("beans-cglib.xml");

        // 2.获取bean
        UserService dbLoginService = (UserService) applicationContext.getBean("dbUserService");
        UserService loginService = (UserService) applicationContext.getBean("cacheUserService");
        PriceService priceService = (PriceService) applicationContext.getBean("priceService");

        // 3. 分别执行，我们这里甚至于可以代理没有实现UserService的接口
        dbLoginService.login();
        loginService.login();
        priceService.processPrice();
    }

}
