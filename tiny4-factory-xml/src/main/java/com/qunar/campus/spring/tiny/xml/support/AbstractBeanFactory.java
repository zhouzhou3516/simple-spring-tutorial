package com.qunar.campus.spring.tiny.xml.support;


import com.google.common.base.Preconditions;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Description: this is bean factory
 *
 * @author yushen.ma
 * @version 2015-03-15 18:31
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    /**
     * 这里使用linkedHashMap
     * 因为必须要严格控制加载顺序。
     * 在Spring中，首先会分析依赖关系，然后先实例化那些对其他对象没有引用的BeanDefinition
     * 这里为了简单起见，不实现这个功能，遇到循环依赖的问题，容器就崩了。。
     */
	private Map<String, BeanDefinition> beanDefinitionMap = new LinkedHashMap<String, BeanDefinition>();

    protected AtomicBoolean init = new AtomicBoolean(false);

	@Override
    public Object getBean(String name) {
        Preconditions.checkArgument(null != beanDefinitionMap.get(name),
                MessageFormat.format("Bean {0} is not defined", name));
		if (null == beanDefinitionMap.get(name).getBean()) {
            beanDefinitionMap.get(name).setBean(doCreateBean(beanDefinitionMap.get(name)));
        }
        return beanDefinitionMap.get(name).getBean();
	}

	@Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
	}

    @Override
    public void init() {
        if (init.compareAndSet(false, true)) {
            for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
                Object bean = doCreateBean(entry.getValue());
                entry.getValue().setBean(bean);
            }
        }
    }

    public Collection<BeanDefinition> getAllBeanDefinition() {
        return beanDefinitionMap.values();
    }

    /**
     * 初始化bean
     * @param beanDefinition a definition
     * @return target object
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition);

}
