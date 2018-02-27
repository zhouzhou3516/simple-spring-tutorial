package com.qunar.campus.spring.tutorial.application;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.application.case3.auto.detect.PriceService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description: AutoDetectTest
 *
 * 这是第三个测试
 *
 * @author yushen.ma
 * @version 2015-03-19 21:48
 */
@Synopsis(name="auto detected", difficulty = Difficulty.EASY)
public class AutoDetectTest {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testAutoDetect() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(new String[] {"autoDetect.xml"});
        PriceService priceService= context.getBean(PriceService.class);
        logger.info("{}", priceService.getDailyPrice(1, 2, "2015-03-08", "2015-03-10"));
    }

}
