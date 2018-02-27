package com.qunar.campus.spring.tiny.context;

import com.qunar.campus.spring.tiny.xml.io.ResourceLoader;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;
import com.qunar.campus.spring.tiny.xml.xml.XmlBeanDefinitionReader;
import com.qunar.campus.spring.tiny.xml.xml.XmlBeanRefSupportReader;

import java.util.Map;

/**
 * Description: XmlApplicationContext
 *
 * 这是一个基于XML配置的
 *
 * @author yushen.ma
 * @version 2015-03-20 11:59
 */
public class XmlApplicationContext extends AbstractApplicationContext {

    public XmlApplicationContext(String pathToFile) {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanRefSupportReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(pathToFile);

        // 2.初始化BeanFactory并注册bean
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            this.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
        // 3. init
        this.init();
    }
}
