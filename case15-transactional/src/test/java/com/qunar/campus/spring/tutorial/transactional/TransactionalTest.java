package com.qunar.campus.spring.tutorial.transactional;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import com.qunar.campus.spring.tutorial.bean.Money;
import com.qunar.campus.spring.tutorial.transaction.aop.bean.Account;
import com.qunar.campus.spring.tutorial.transaction.aop.service.TradeService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description: TemplateTest
 *
 * 这是一个spring标准的transactional的测试
 *
 * 把注释掉的地方去掉就可以运行。
 * 因为测试一起跑的时候数据库服务器端口冲突，所以注掉了。
 * 单独运行是ok的
 *
 * @author yushen.ma
 * @version 2015-03-23 00:27
 */
@Synopsis(name="a transaction test", difficulty = Difficulty.NORMAL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:case1-transactional.xml")
public class TransactionalTest {

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    TradeService tradeService;

    @Test
    public void testTransaction() {
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
        // 有一个没更新成功，但是扣款已经成功了。。
    }

}
