package com.qunar.campus.spring.tiny.aspect.auto;

import com.qunar.campus.spring.tiny.context.ApplicationContext;
import com.qunar.campus.spring.tiny.context.XmlApplicationContext;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Test;


/**
 * @author yushen.ma
 * @version 2015-03-21 20:00
 */
@Synopsis(name="AspectJ Wave", difficulty = Difficulty.NIGHT_MARE)
public class AutoAOPTest {

    @Test
    public void testXMLFactory() {

        // 1.读取配置
        ApplicationContext applicationContext = new XmlApplicationContext("beans-auto-aop.xml");

        // 2.获取bean
        UserService dbLoginService = (UserService) applicationContext.getBean("dbUserService");
        UserService loginService = (UserService) applicationContext.getBean("cacheUserService");
        PriceService priceService = (PriceService) applicationContext.getBean("priceService");

        // 3. 分别执行，我们这里甚至于可以代理没有实现UserService的接口
        dbLoginService.login();
        loginService.login();
        priceService.processPrice();//这个方法会命中AOP
        priceService.doNothing();
    }

}
