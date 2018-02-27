package com.qunar.campus.spring.tiny.sample;

/**
 * Description: this is bean definition
 *
 * @author yushen.ma
 * @version 2015-03-15 17:29
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
