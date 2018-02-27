package com.qunar.campus.spring.tutorial.bean.lifecycle.base;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by liqingzhou on 17/8/1.
 */
public class BeanWithAnnotationTest {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[]{"beans.xml"});
        BeanWithInitDefinition beanWithInitDefinition = context
                .getBean(BeanWithInitDefinition.class);
        //beanWithInitDefinition.init();

    }

}
