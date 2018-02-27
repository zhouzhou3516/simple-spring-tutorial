package com.qunar.campus.spring.tiny.factory;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
public class BeanDefinition {

	private Object bean;

	private Class beanClass;

	private String beanClassName;

	public BeanDefinition() {
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Object getBean() {
		return bean;
	}

}
