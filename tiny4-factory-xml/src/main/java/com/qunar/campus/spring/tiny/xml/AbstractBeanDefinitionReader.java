package com.qunar.campus.spring.tiny.xml;

import com.qunar.campus.spring.tiny.xml.io.ResourceLoader;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 从配置中读取BeanDefinitionReader
 *
 * @author yushen.ma
 * @version 2015-03-16 21:28
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String,BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<String, BeanDefinition>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
