package com.qunar.campus.spring.tutorial.transaction.aop.annotation;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description: ProceedingJoinPoint
 *
 * 访问连接点
 *
 * @author yushen.ma
 * @version 2015-03-23 23:37
 */
@Component
@Aspect
@Synopsis(difficulty = Difficulty.EASY, name = "advice")
public class ProceedingJoinPoint {

    private static Logger logger = LoggerFactory.getLogger(ProceedingJoinPoint.class);

    /**
     * 环绕增强
     *
     * 我们当然需要拿到joinPoint 才能继续执行
     * 额，这个看起来还挺像Transactional标签的解释呢
     * 在实际的方法调用前，开启事务，在完成后提交事务，在异常时，回滚事务..
     * 所以呢，在下一个case里面会有一个transactional的完整实现
     *
     * @param joinPoint joinPoint
     */
    @Around("execution(* serveTo(..))")
    public Object AroundGreet(org.aspectj.lang.ProceedingJoinPoint joinPoint) {
        logger.info("transactional begin");
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.warn("transactional rollback");
        }
        logger.info("transactional commit");
        return result;
    }

    /**
     * 动态获取切点的参数
     * 名字要对应
     */
    @Before("execution(* iHaveSomeParams(..)) && args(i, user)")
    public void BindJoinPointParams(int i, String user) {
        logger.info("--- start BindJoinPointParams {}");
        logger.info("--- i {}", i);
        logger.info("--- name {}", user);
        logger.info("--- end BindJoinPointParams {}");
    }

    /**
     * this 和 target 可以绑定代理对象
     * 该切点表示，所有代理对象为Waiter的类的所有方法都会匹配到这个切点
     * @param waiter waiter
     */
    @Before("this(waiter)")
    public void bindJoinPointObject(Waiter waiter) {
        logger.info("bind object start");
        logger.info("{}", waiter.getClass().getName());
        logger.info("bind object end");
    }


}
