package com.qunar.campus.spring.tutorial.transaction.aop.annotation;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Description: AdviceSynopsis
 *
 * @author yushen.ma
 * @version 2015-03-23 23:23
 */
@Component
@Aspect
@Synopsis(difficulty = Difficulty.EASY, name = "advice")
public class AdviceSynopsis {

    final private Logger logger = getLogger(getClass());

    /**
     * 方法切入 execution() 方法匹配模式串 ： 表示满足某一匹配模式的所有目标类方法连接点
     * 这样写即描述了一个切点
     * 也描述了一个增强  :p
     *
     * 这种声明切点的方式叫做匿名切点，匿名切点只能在声明处使用。
     * 如果希望在其他地方重用一个切点，我们可以通过@Pointcut来声明一个切点
     */
    @Before("execution(* greetTo(..))")
    public void beforeGreetTo(JoinPoint joinPoint) {
        logger.info(joinPoint.getSourceLocation().toString());
        logger.info("before greetTO");
    }

    @AfterReturning("execution(* greetTo(..))")
    public void afterGreetTo() {
        logger.info("after greet to");
    }

    @AfterThrowing("execution(* greetTo(..))")
    public void onThrow() {
        logger.info("on throw greet to");
    }

}
