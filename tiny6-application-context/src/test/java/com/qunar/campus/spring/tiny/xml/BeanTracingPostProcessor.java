package com.qunar.campus.spring.tiny.xml;

import com.qunar.campus.spring.tiny.context.BeanPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: BeanTracingPostProcessor
 *
 * 这个类用于测试BeanPostProcessor的功能
 *
 * 打印出每个实例化的类
 *
 * @author yushen.ma
 * @version 2015-03-20 13:17
 */
public class BeanTracingPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        logger.info("bean: {}, create : {}", beanName, bean);
        return bean;
    }
}
