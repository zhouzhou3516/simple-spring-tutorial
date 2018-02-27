package com.qunar.campus.spring.tiny.context;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.qunar.campus.spring.tiny.xml.support.BeanDefinition;
import com.qunar.campus.spring.tiny.xml.support.DependencyInjectionFactory;

import java.util.Collection;
import java.util.List;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Description: AbstractApplicationContext
 *
 * @author yushen.ma
 * @version 2015-03-20 11:10
 */
public class AbstractApplicationContext extends DependencyInjectionFactory implements ApplicationContext {

    final protected CopyOnWriteArrayList<BeanPostProcessor> beanPostProcessors = Lists.newCopyOnWriteArrayList();

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            Object bean = createBeanInstance(beanDefinition);
            //add some call backs
            bean = beforePropertySet(bean, beanDefinition);
            applyPropertyValues(bean, beanDefinition);
            //add some call backs
            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }
            //add some call backs
            bean = afterPropertySet(bean, beanDefinition);
            return bean;
        } catch (Throwable t) {
            throw Throwables.propagate(t);
        }
    }

    protected Object afterPropertySet(Object bean, BeanDefinition beanDefinition) {
        //add some call backs
        for (BeanPostProcessor postProcessor : beanPostProcessors) {
            bean = postProcessor.postProcessAfterInitialization(bean, beanDefinition.getBeanClassName());
        }
        return bean;
    }

    protected Object beforePropertySet(Object bean, BeanDefinition beanDefinition) {
        //add some call backs
        for (BeanPostProcessor postProcessor : beanPostProcessors) {
            bean = postProcessor.postProcessBeforeInitialization(bean, beanDefinition.getBeanClassName());
        }
        return bean;
    }

    @Override
    public void init() {
        if (!init.compareAndSet(false, true)) {
            return;
        }
        //find all the call backs
        Collection<BeanDefinition> allBeanDefinition = getAllBeanDefinition();
        final List<BeanDefinition> beanPostProcessorsDefinition = Lists.newArrayList();
        Iterables.removeIf(allBeanDefinition, new Predicate<BeanDefinition>() {
            @Override
            public boolean apply(BeanDefinition input) {
                boolean assignableFrom = BeanPostProcessor.class.isAssignableFrom(input.getBeanClass());
                if (assignableFrom) {
                    beanPostProcessorsDefinition.add(input);
                }
                return false;
            }
        });

        // init post processor, 注意，这些postProcessor里面的ref标签是不起作用的。
        // 其实在Spring中也是类似,其中滋味，自己体会
        for (BeanDefinition postProcessor: beanPostProcessorsDefinition) {
            this.beanPostProcessors.add((BeanPostProcessor) doCreateBean(postProcessor));
        }
        for (BeanDefinition beanDefinition : allBeanDefinition) {
            Object bean = doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
        }
    }
}
