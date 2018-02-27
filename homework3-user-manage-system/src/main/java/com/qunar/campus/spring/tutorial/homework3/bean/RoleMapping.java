package com.qunar.campus.spring.tutorial.homework3.bean;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Description: RoleMapping
 *
 * 关系表
 *
 * @author yushen.ma
 * @version 2015-03-30 10:07
 */
public class RoleMapping implements Serializable {

    /**
     * 映射名称
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 角色id
     */
    private int roleId;

    public RoleMapping() {
    }

    public RoleMapping(long id, long userId, int roleId) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder
                .toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
