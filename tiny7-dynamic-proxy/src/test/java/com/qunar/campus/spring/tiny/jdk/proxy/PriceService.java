package com.qunar.campus.spring.tiny.jdk.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: PriceService
 *
 * @author yushen.ma
 * @version 2015-03-21 20:00
 */
public class PriceService {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    public void processPrice() {
        logger.info("price init start");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
        logger.info("price init done");
    }

}
