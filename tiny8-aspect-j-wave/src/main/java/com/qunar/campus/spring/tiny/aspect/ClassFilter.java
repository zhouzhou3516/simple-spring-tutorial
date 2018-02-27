package com.qunar.campus.spring.tiny.aspect;

/**
 *
 * 类匹配接口
 *
 * @author yushen.ma
 * @version 2015-03-20 15:20
 */
public interface ClassFilter {

    boolean matches(Class targetClass);
}
