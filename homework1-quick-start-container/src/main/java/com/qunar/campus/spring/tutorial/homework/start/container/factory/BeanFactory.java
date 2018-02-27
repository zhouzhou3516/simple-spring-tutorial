package com.qunar.campus.spring.tutorial.homework.start.container.factory;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.campus.spring.tutorial.homework.start.container.beans.BeanDefinition;
import com.qunar.campus.spring.tutorial.homework.start.container.beans.BeanReference;
import com.qunar.campus.spring.tutorial.homework.start.container.beans.PropertyValue;
import com.qunar.campus.spring.tutorial.homework.start.container.io.ResourceLoader;
import com.qunar.campus.spring.tutorial.homework.start.container.xml.XmlBeanDefinitionReader;
import com.qunar.campus.spring.tutorial.homework.start.dao.JdbcDataSource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by liqingzhou on 17/7/21.
 */
public class BeanFactory implements Factory {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final List<String> beanDefinitionIds = Lists.newArrayList();
    private String configLocation;
    //bean id 作为键值 :id，支持通过 bean id 注入引用
    private Map<String, BeanDefinition> nameIndexedBeanDefinitionMap = Maps.newConcurrentMap();


    public BeanFactory(String configLocation) throws Exception {
        this.configLocation = configLocation;

        BeanDefinition jdbcBean = new BeanDefinition();
        jdbcBean.setBean(JdbcDataSource.getJdbcTemplate());
        jdbcBean.setBeanId("jdbcTemplate");
        // jdbcTemplate
        nameIndexedBeanDefinitionMap.put(jdbcBean.getBeanId(), jdbcBean);
        logger.info("==========first init jdbcTemplate=======");
        loadBeanDefinitions();
        preInstantiateSingletons();
    }

    private void loadBeanDefinitions() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(
                new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        for (BeanDefinition beanDefinition : xmlBeanDefinitionReader
                .getRegistry()) {
            registerBeanDefinition(beanDefinition);
        }
    }

    private void preInstantiateSingletons() throws Exception {
        for (Iterator it = this.beanDefinitionIds.iterator(); it.hasNext(); ) {
            String beanId = (String) it.next();
            getBean(beanId);
        }
    }


    public <T> T getBean(String beanId) throws Exception {
        BeanDefinition beanDefinition = nameIndexedBeanDefinitionMap.get(beanId);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + beanId + " is defined");
        }
        return createBeanIfNull(beanDefinition);
    }

    private <T> T createBeanIfNull(BeanDefinition beanDefinition) throws Exception {
        T bean = (T) beanDefinition.getBean();
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
        }
        return bean;
    }


    private <T> T createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        T bean = (T) beanDefinition.getBeanClass().newInstance();
        if (StringUtils.isNotBlank(beanDefinition.getInitMethod())) {
            Method init = beanDefinition.getBeanClass()
                    .getDeclaredMethod(beanDefinition.getInitMethod());

            init.invoke(bean);

        }
        return bean;
    }

    private void registerBeanDefinition(BeanDefinition beanDefinition)
            throws Exception {
        nameIndexedBeanDefinitionMap.put(beanDefinition.getBeanId(), beanDefinition);
        beanDefinitionIds.add(beanDefinition.getBeanId());
    }


    private <T> T doCreateBean(BeanDefinition beanDefinition) throws Exception {
        T bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;

                value = getBean(beanReference.getName());


            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod(
                        "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                + propertyValue.getName().substring(1), value.getClass());
                declaredMethod.setAccessible(true);

                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }


}
