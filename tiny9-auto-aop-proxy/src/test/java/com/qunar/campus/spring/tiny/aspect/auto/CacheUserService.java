package com.qunar.campus.spring.tiny.aspect.auto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: CacheUserService
 *
 * @author yushen.ma
 * @version 2015-03-20 13:50
 */
public class CacheUserService implements UserService {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void login() {
        logger.info("cache user service");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }
}
