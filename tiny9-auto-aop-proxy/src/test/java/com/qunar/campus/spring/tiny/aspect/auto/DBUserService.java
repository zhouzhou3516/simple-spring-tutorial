package com.qunar.campus.spring.tiny.aspect.auto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: DBUserService
 *
 * @author yushen.ma
 * @version 2015-03-20 13:49
 */
public class DBUserService implements UserService {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void login() {
        logger.info("db user service");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.interrupted();//这句话是什么意思呢？
        }
    }
}
