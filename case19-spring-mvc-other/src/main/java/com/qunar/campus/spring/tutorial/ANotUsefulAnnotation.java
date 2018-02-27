package com.qunar.campus.spring.tutorial;

import java.lang.annotation.*;

/**
 * Description: ANotUsefulAnnotation
 *
 * 只是为了测试
 *
 * @author yushen.ma
 * @version 2015-04-04 17:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
@Documented
public @interface ANotUsefulAnnotation {
}
