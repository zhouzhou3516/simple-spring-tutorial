package com.qunar.campus.spring.tutorial.container.extension.points;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Description: InstantiationTracingBeanPostProcessor
 *
 * @see org.springframework.beans.factory.config.BeanPostProcessor
 *
 * important : An ApplicationContext automatically detects any beans that are
 * defined in the configuration metadata which implement the BeanPostProcessor interface.
 * The ApplicationContext registers these beans as post-processors so that they can be
 * called later upon bean creation. Bean post-processors can be deployed in the
 * container just like any other beans.
 *
 * 很多spring的小功能都是基于该接口实现的
 * AOP auto-proxying 也是基于这个接口实现的
 * 你可以查看以下该接口的实现类，就大概知道有哪些场景下可以使用 :)
 *
 * @author yushen.ma
 * @version 2015-03-19 22:48
 */
@Synopsis(name = "beanPostProcessor", difficulty = Difficulty.NORMAL)
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    // simply return the instantiated bean as-is
    // 在set properties方法之前
    public Object postProcessBeforeInitialization(Object bean,
                                                  String beanName) throws BeansException {
        return bean; // we could potentially return any object reference here...
    }

    // 在set properties方法之后
    public Object postProcessAfterInitialization(Object bean,
                                                 String beanName) throws BeansException {
        logger.info("bean : {}, created : {}", beanName, bean.toString());
        return bean;
    }
}
