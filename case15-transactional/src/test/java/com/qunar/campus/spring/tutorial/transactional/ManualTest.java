package com.qunar.campus.spring.tutorial.transactional;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.bean.Money;
import com.qunar.campus.spring.tutorial.transaction.aop.bean.Account;
import com.qunar.campus.spring.tutorial.transaction.aop.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Description: ManualTest
 *
 * 这是一个自己动手的spring transactional的实现
 *
 * @author yushen.ma
 * @version 2015-03-26 15:49
 */
@Synopsis(name="a transaction impl test", difficulty = Difficulty.NORMAL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:case2-manual.xml")
public class ManualTest {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    TradeService tradeService;

    @Test
    public void test() {
        // init user
        Account tom = new Account(1, Money.of(new BigDecimal("100.0")));
        Account jerry = new Account(2, Money.of(new BigDecimal("30.0")));
        tradeService.createAccount(tom);
        tradeService.createAccount(jerry);
        try {
            tradeService.transfers(tom, jerry, Money.of(BigDecimal.TEN), true);
        } catch (Throwable ignore) {
            logger.info("here is a error", ignore);
        }

        logger.info("{}", tradeService.find(1));// 90
        logger.info("{}", tradeService.find(2));// 30
    }

}
