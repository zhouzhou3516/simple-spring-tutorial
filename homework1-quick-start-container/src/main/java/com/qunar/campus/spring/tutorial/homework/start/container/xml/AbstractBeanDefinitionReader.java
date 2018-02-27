package com.qunar.campus.spring.tutorial.homework.start.container.xml;

import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.homework.start.container.beans.BeanDefinition;
import com.qunar.campus.spring.tutorial.homework.start.container.io.ResourceLoader;
import java.util.List;

/**
 * Created by liqingzhou on 17/7/21.
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private List<BeanDefinition> registry = Lists.newArrayList();

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }


}
