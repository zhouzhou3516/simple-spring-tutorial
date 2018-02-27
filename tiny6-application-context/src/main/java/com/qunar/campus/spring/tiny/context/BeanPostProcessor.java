package com.qunar.campus.spring.tiny.context;

/**
 * Description: BeanPostProcessor
 *
 * 特别注意：
 * Note that if you have beans wired into your BeanPostProcessor using
 * autowiring or @Resource (which may fall back to autowiring),
 * Spring might access unexpected beans when searching for
 * type-matching dependency candidates, and therefore make them ineligible
 * for auto-proxying or other kinds of bean post-processing.
 * For example, if you have a dependency annotated with @Resource
 * where the field/setter name does not directly correspond to the
 * declared name of a bean and no name attribute is used, then Spring will
 * access other beans for matching them by type.
 *
 * 简而言之：BeanPostProcessor里面的属性的生命周期很奇怪，可能不能被BeanPostProcessor处理
 *
 * 另外，BeanPostProcessor之间也有顺序可言， 先声明的可以处理后声明的
 *
 * @author yushen.ma
 * @version 2015-03-20 11:27
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);

}
