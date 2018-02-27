package com.qunar.campus.spring.tiny.sample;


import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.junit.Assert;
import org.junit.Test;

/**
 * Description: this is a test
 *
 * @author yushen.ma
 * @version 2015-03-15 16:11
 */
@Synopsis(name="simple bean factory", difficulty = Difficulty.EASY)
public class SimpleBeanFactoryTest {

    @Test
    public void testGetBean() throws Exception {
        // 1.初始化beanFactory
        SimpleBeanFactory simpleBeanFactory = new SimpleBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition(new HelloWorldService());
        simpleBeanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) simpleBeanFactory.getBean("helloWorldService");
        Assert.assertEquals("hello world", helloWorldService.helloWorld());
    }
}