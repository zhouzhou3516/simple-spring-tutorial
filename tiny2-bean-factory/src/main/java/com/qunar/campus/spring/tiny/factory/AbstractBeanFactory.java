package com.qunar.campus.spring.tiny.factory;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
public abstract class AbstractBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	@Override
    public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	@Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        beanDefinitionMap.put(name, beanDefinition);
	}

    /**
     * 初始化bean
     * @param beanDefinition a definition
     * @return target object
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition);

}
