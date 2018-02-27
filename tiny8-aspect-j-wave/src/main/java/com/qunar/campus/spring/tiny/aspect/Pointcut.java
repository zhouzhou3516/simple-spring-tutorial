package com.qunar.campus.spring.tiny.aspect;

/**
 *
 * 通过类匹配，方法匹配，我们就可以描述出一个切点
 *
 * @author yushen.ma
 * @version 2015-03-20 15:20
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
