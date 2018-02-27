package com.qunar.campus.spring.meta.data;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Description: ToStringFactory
 *
 * @author yushen.ma
 * @version 2015-03-23 14:09
 */
public class ToStringUtils {

    private static Joiner joiner = Joiner.on(", \n");

    public static String toString(Object o) {
        String str = o.getClass().getSimpleName() + " { \n";
        Field[] declaredFields = o.getClass().getDeclaredFields();
        List<String> fields = Lists.newArrayList();
        if (declaredFields != null && declaredFields.length != 0) {
            for (Field item : declaredFields) {
                if (null != item.getAnnotation(ToStringIgnore.class)) {
                    continue;
                }
                item.setAccessible(true);
                String field = "";
                try {
                    field = item.getName() + ":" + item.get(o);
                } catch (IllegalAccessException ignore) {}
                fields.add(field);
            }
        }
        str += joiner.join(fields) + " \n} ";
        return str;
    }
}
