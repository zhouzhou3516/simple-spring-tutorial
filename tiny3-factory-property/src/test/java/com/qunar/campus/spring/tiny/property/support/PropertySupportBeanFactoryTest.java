package com.qunar.campus.spring.tiny.property.support;

import com.qunar.campus.spring.tiny.property.PropertyValue;
import com.qunar.campus.spring.tiny.property.PropertyValues;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Assert;
import org.junit.Test;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
@Synopsis(name="property support bean factory", difficulty = Difficulty.EASY)
public class PropertySupportBeanFactoryTest {

    @Test
    public void testDoCreateBean() throws Exception {
        // 1.初始化bean factory
        BeanFactory beanFactory = new PropertySupportBeanFactory();

        // 2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.qunar.campus.spring.tiny.property.support.HelloWorldService");

        // 3.设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text", "Hello World!"));
        beanDefinition.setPropertyValues(propertyValues);

        // 4.生成bean
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 5.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        Assert.assertEquals("Hello World!", helloWorldService.helloWorld());

    }
}