package com.qunar.campus.spring.tiny.factory;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
public class SimpleInitBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            // just a easy init
            return beanDefinition.getBeanClass().newInstance();
        } catch (Throwable t) {
            throw new BeanInitFailedException(t);
        }
    }

    private static class BeanInitFailedException extends RuntimeException {
        public BeanInitFailedException(Throwable t) { }
    }

}
