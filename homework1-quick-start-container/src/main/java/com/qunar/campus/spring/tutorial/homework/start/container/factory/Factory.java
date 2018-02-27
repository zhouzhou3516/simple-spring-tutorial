package com.qunar.campus.spring.tutorial.homework.start.container.factory;

/**
 * @author zhangxin
 *         Created on 17/7/19.
 */
public interface Factory {
    <T> T getBean(String beanId) throws  Exception;
}
