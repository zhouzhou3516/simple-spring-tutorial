package com.qunar.campus.spring.tiny.jdk.proxy.test;

import com.qunar.campus.spring.tiny.context.ApplicationContext;
import com.qunar.campus.spring.tiny.context.XmlApplicationContext;
import com.qunar.campus.spring.tiny.jdk.proxy.UserService;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Test;


/**
 * Description: BeanFactoryTest
 *
 * @author yushen.ma
 * @version 2015-03-20 09:33
 */
@Synopsis(name="JDK proxy", difficulty = Difficulty.HARD)
public class JDKProxyTest {

    @Test
    public void testXMLFactory() {

        // 1.读取配置
        ApplicationContext applicationContext = new XmlApplicationContext("beans-jdk.xml");

        // 2.获取bean
        UserService dbLoginService = (UserService) applicationContext.getBean("dbUserService");
        UserService loginService = (UserService) applicationContext.getBean("cacheUserService");

        // 3.分别执行
        dbLoginService.login();
        loginService.login();
    }
}
