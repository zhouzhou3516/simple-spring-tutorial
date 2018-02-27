package com.qunar.campus.spring.tiny.xml;

import com.qunar.campus.spring.tiny.xml.io.ResourceLoader;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;
import com.qunar.campus.spring.tiny.xml.support.BeanFactory;
import com.qunar.campus.spring.tiny.xml.support.PropertySupportBeanFactory;
import com.qunar.campus.spring.tiny.xml.xml.XmlBeanDefinitionReader;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Test;

import java.util.Map;

/**
 * Description: BeanFactoryTest
 *
 * @author yushen.ma
 * @version 2015-03-20 09:33
 */
@Synopsis(name="xml bean factory", difficulty = Difficulty.EASY)
public class BeanFactoryTest {

    @Test
    public void testXMLFactory() {

        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("beans.xml");

        // 2.初始化BeanFactory并注册bean
        BeanFactory beanFactory = new PropertySupportBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3. init
        beanFactory.init();

        // 4.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();

    }
}
