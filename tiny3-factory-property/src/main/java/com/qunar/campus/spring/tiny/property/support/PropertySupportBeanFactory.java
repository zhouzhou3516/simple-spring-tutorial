package com.qunar.campus.spring.tiny.property.support;

import com.google.common.base.Throwables;
import com.qunar.campus.spring.tiny.property.PropertyValue;

import java.lang.reflect.Field;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
public class PropertySupportBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean = createBeanInstance(beanDefinition);
            applyPropertyValues(bean, beanDefinition);
            return bean;
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
	}

	protected Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
	}

	protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws NoSuchFieldException, IllegalAccessException {
		for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
			Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
			declaredField.setAccessible(true);
			declaredField.set(bean, propertyValue.getValue());
		}
	}
}
