package com.qunar.campus.spring.tutorial.application;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.application.case2.auto.annotation.UserService;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: AnnotationTest
 *
 * 这是第二个测试
 *
 * @author yushen.ma
 * @version 2015-03-19 21:16
 */
@Synopsis(name="beanFactory annotation", difficulty = Difficulty.EASY)
public class AnnotationTest {

    @Test
    public void testAnnotation() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"annotation.xml"});
        UserService userService = context.getBean(UserService.class);
        Assert.assertEquals("yushen.ma", userService.getUser());
    }
}
