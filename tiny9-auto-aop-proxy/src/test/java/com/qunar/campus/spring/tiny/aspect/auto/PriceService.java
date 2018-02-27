package com.qunar.campus.spring.tiny.aspect.auto;

import org.apache.commons.lang3.SerializationUtils;
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

    public void doNothing() {
        logger.info("do nothing");
    }

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
