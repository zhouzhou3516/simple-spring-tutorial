package com.qunar.campus.spring.tiny.xml;

import com.qunar.campus.spring.tiny.context.ApplicationContext;
import com.qunar.campus.spring.tiny.context.XmlApplicationContext;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import junit.framework.Assert;
import org.junit.Test;


/**
 * Description: BeanFactoryTest
 *
 * @author yushen.ma
 * @version 2015-03-20 09:33
 */
@Synopsis(name="simple application context", difficulty = Difficulty.EASY)
public class ApplicationContextTest {

    @Test
    public void testXMLFactory() {

        // 1.读取配置
        ApplicationContext applicationContext = new XmlApplicationContext("beans-ref.xml");

        // 4.获取bean
        LoginService loginService = (LoginService) applicationContext.getBean("loginService");
        Assert.assertEquals("Hello World!", loginService.login("admin", "123"));
    }
}
