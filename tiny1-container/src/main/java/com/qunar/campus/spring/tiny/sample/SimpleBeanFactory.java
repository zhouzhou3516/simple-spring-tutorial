package com.qunar.campus.spring.tiny.sample;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 17:31
 */
public class SimpleBeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	public Object getBean(String name) {
		return beanDefinitionMap.get(name).getBean();
	}

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(name, beanDefinition);
	}

}
