package com.qunar.campus.spring.meta.data;

import java.lang.annotation.*;

/**
 * Description: ToStringIgnore
 *
 * 这个方法表示ToString方法忽略该字段
 *
 * @author yushen.ma
 * @version 2015-03-23 14:06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface ToStringIgnore {

}
