package com.qunar.campus.spring.tiny.xml.support;

import com.qunar.campus.spring.tiny.xml.PropertyValue;

import java.lang.reflect.Field;

/**
 * Description: DependencyInjectionFactory
 *
 * @author yushen.ma
 * @version 2015-03-20 10:14
 */
public class DependencyInjectionFactory extends PropertySupportBeanFactory {

    /**
     * 覆盖property方法，增加对ref的支持
     */
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        for (PropertyValue property : beanDefinition.getPropertyValues().getPropertyValues()) {
            Field declaredField = bean.getClass().getDeclaredField(property.getName());
            declaredField.setAccessible(true);
            declaredField.set(bean, this.propertyEdit(property));
        }
    }

    protected Object propertyEdit(PropertyValue property) {
        if (null != property.getValue()) {
            return property.getValue();
        } else if (null != property.getRef() && property.getRef() instanceof String) {
            return this.getBean((String) property.getRef());
        }
        throw new PropertyNotFound(property.toString());
    }

    public static class PropertyNotFound extends RuntimeException {
        public PropertyNotFound(String message) {
            super(message);
        }
    }
}
