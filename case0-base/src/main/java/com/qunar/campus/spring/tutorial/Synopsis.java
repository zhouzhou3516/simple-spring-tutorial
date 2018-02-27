package com.qunar.campus.spring.tutorial;

import java.lang.annotation.*;

/**
 * Description: Synopsis
 *
 * @author yushen.ma
 * @version 2015-03-19 22:57
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface Synopsis {

    /**
     * name for this synopsis. naming is really a hard thing.
     */
    String name();

    /**
     * how hard is it
     */
    Difficulty difficulty() default Difficulty.EASY;

    /**
     * related class for this
     */
    Class<?>[] related() default {};
}
