package com.qunar.campus.spring.tiny.aspect;

import java.lang.reflect.Method;

/**
 *
 * 方法匹配接口
 *
 * @author yushen.ma
 * @version 2015-03-20 15:20
 */
public interface MethodMatcher {

    boolean matches(Method method, Class targetClass);
}
