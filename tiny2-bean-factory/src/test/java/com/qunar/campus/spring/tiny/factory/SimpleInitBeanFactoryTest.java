package com.qunar.campus.spring.tiny.factory;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Assert;

@Synopsis(name="simple init bean factory", difficulty = Difficulty.EASY)
public class SimpleInitBeanFactoryTest {

    @org.junit.Test
    public void testDoCreateBean() throws Exception {
        // 1.初始化bean factory
        BeanFactory beanFactory = new SimpleInitBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.qunar.campus.spring.tiny.factory.HelloWorldService");
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        Assert.assertEquals("hello world", helloWorldService.helloWorld());
    }
}