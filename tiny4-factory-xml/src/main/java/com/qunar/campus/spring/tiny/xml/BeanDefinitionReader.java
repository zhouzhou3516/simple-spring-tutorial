package com.qunar.campus.spring.tiny.xml;

/**
 * 从配置中读取BeanDefinitionReader
 *
 * @author yushen.ma
 * @version 2015-03-16 21:28
 */
public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;

}
