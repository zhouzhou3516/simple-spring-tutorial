package com.qunar.campus.spring.tutorial.homework.start.container.context;

import com.qunar.campus.spring.tutorial.homework.start.container.factory.BeanFactory;
import com.qunar.campus.spring.tutorial.homework.start.container.factory.Factory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by liqingzhou on 17/8/2.
 */
public class MyApplicationContext implements Factory {

    /**
     * Default config location for the root context
     */
    public static final String DEFAULT_CONFIG_LOCATION = "my-bean.xml";
    Logger logger = LoggerFactory.getLogger(getClass());
    Factory factory;

    public MyApplicationContext(String configLocation) {
        try {
            if (StringUtils.isBlank(configLocation)) {
                configLocation = DEFAULT_CONFIG_LOCATION;
            }
            factory = new BeanFactory(configLocation);
        } catch (Exception e) {
            logger.error("error to load config file:{},{}", configLocation, e);
        }
    }



    @Override
    public <T> T getBean(String beanId) {
        try {
            return factory.getBean(beanId);
        } catch (Exception e) {
            logger.error("error to get Bean:{},{}" , beanId,e);
        }
        return null;
    }
}
