package com.qunar.campus.spring.tiny.xml;

import com.qunar.campus.spring.tiny.xml.io.ResourceLoader;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;
import com.qunar.campus.spring.tiny.xml.support.BeanFactory;
import com.qunar.campus.spring.tiny.xml.support.DependencyInjectionFactory;
import com.qunar.campus.spring.tiny.xml.xml.XmlBeanDefinitionReader;
import com.qunar.campus.spring.tiny.xml.xml.XmlBeanRefSupportReader;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import junit.framework.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Description: BeanFactoryTest
 *
 * @author yushen.ma
 * @version 2015-03-20 09:33
 */
@Synopsis(name="simple inject bean factory", difficulty = Difficulty.EASY)
public class InjectTest {

    @Test
    public void testXMLFactory() {

        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanRefSupportReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("beans-ref.xml");

        // 2.初始化BeanFactory并注册bean
        BeanFactory beanFactory = new DependencyInjectionFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3. init
        beanFactory.init();

        // 4.获取bean
        LoginService loginService = (LoginService) beanFactory.getBean("loginService");
        Assert.assertEquals("Hello World!", loginService.login("admin", "123"));
    }
}
