package com.qunar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * com.qunar
 *
 * @author yushen.ma
 * @version 1.0 2015-11-29
 */
public class HelloWorldTest {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testSay() throws Exception {
        XmlBeanFactory ioc = new XmlBeanFactory(new ClassPathResource("hello-world-beans.xml"));
        HelloWorld helloWorld = ioc.getBean(HelloWorld.class);
        logger.info("{}", helloWorld.say());
    }
}