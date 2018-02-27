package com.qunar.campus.spring.tutorial.container.extension.points;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Description: BeanFactoryPostProcessor
 *
 * 该类有个最最常见的例子就是
 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
 *
 * 他的工作原理就是，在IoC容器读入配置之后，生成了BeanDefinition对象，然后该BeanFactoryPostProcessor将
 * BeanDefinition中带有${}这样符号的字符串替换为配置文件中的配置，
 *
 * @author yushen.ma
 * @version 2015-03-19 23:10
 */
@Synopsis(name = "beanFactoryPostProcessor", difficulty = Difficulty.NORMAL)
public class BeanFactoryPostProcessorSynopsis implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
