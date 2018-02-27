package com.qunar.campus.spring.tutorial.homework3.bean;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Description: RoleInfo
 *
 * 角色
 *
 * @author yushen.ma
 * @version 2015-03-30 10:02
 */
public class Role implements Serializable {

    /**
     * 角色id
     */
    private int id;

    /**
     * 角色名
     */
    private String name;

    public Role() {
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder
                .toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
