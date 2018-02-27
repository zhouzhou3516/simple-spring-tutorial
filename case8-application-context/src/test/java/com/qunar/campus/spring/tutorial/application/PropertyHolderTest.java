package com.qunar.campus.spring.tutorial.application;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.application.case1.property.holder.FakeDataSource;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Description: AnnotationTest
 *
 * 这是第一个测试
 * @author yushen.ma
 * @version 2015-03-19 21:16
 */
@Synopsis(name="property holder", difficulty = Difficulty.EASY)
public class PropertyHolderTest {

    /**
     * 这是一个关于配置解析器的例子
     * @throws Exception
     */
    @Test
    public void testPropertyHolder() throws Exception {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"bean.xml"});
        FakeDataSource bean = context.getBean(FakeDataSource.class);
        Assert.assertEquals("hello world", bean.doSomething());
    }


}