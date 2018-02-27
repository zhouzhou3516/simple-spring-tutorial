package com.qunar.campus.spring.tiny.aspect;

/**
 *
 * PointcutAdvisor
 * 1. 描述了切点
 * 2. 描述了增强
 *
 * ----> 足够构成一个AOP
 *
 * @author yushen.ma
 * @version 2015-03-20 15:20
 */
public interface PointcutAdvisor extends Advisor{

   Pointcut getPointcut();

}
