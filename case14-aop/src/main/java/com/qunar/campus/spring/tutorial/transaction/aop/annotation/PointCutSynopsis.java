package com.qunar.campus.spring.tutorial.transaction.aop.annotation;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description: TimerAop
 *
 * You may register aspect classes as regular beans in your Spring XML configuration,
 * or autodetect them through classpath scanning - just like any other Spring-managed bean.
 * However, note that the @Aspect annotation is not sufficient for autodetection
 * in the classpath: For that purpose, you need to add a separate @Component
 * annotation (or alternatively a custom stereotype annotation that qualifies,
 * as per the rules of Spring’s component scanner).
 *
 *
 *
 * @author yushen.ma
 * @version 2015-03-23 21:02
 */
@Component
@Aspect
@Synopsis(difficulty = Difficulty.EASY, name = "pointCut")
public class PointCutSynopsis {

    private static Logger logger = LoggerFactory.getLogger(PointCutSynopsis.class);

    /**
     * 显示地声明一个切点
     *
     * 你可以尝试在这个函数内部调用一下 system.out.println
     * 你会发现pointcut 报出一个警告。体会一下这是什么意思
     *
     * 其中Pointcut是显示命名切点的注解， execution那一段是切点表达式
     * public是切点引用修饰符， beforeGreetTo是切点名称
     *
     * 显然，我们可以通过类的继承方式来定义个多的相关切点。。。额 。这功能，好鸡肋
     */
    @Pointcut("execution(* greetTo(..))")
    public void beforeGreetTo() {}

    @Pointcut("execution(* greetTo(..))")
    public void afterGreetTo() { }

    @Pointcut("execution(* greetTo(..))")
    public void onThrow() { }

    /**
     * 这里我们引用一下上面用到的切点
     */
    @Before("com.qunar.campus.spring" +
            ".tutorial.transaction.aop" +
            ".annotation.PointCutSynopsis" +
            ".beforeGreetTo()")
    public void ASimpleAdvice() {
        logger.info("reference to a point cut");
    }

    /**
     * 运行程序你会发现，在AdviceSynopsis中的前置切点被先调用。
     * 嗯...调用关系又是切面中的一个坑
     *
     * 1. 增强在同一个切面类中声明，按照顺序织入
     * 2. 增强位于不同切面类中。且这些类都实现了org.springframework.core.Ordered接口，则按序号确定
     * 3. 如果位于不同切面中，且都没有实现这个接口，织入顺序不一定。我估计是class载入的顺序
     *
     */

}
