package com.qunar.campus.spring.tutorial.homework.start.container.beans;

/**
 * Created by liqingzhou on 17/7/21.
 */
public class BeanReference {

    private String name;
    private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
