package com.qunar.campus.spring.tutorial.homework.start.bean;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Description: UserInfo
 *
 * @author yushen.ma
 * @version 2015-03-30 10:01
 */
public class UserInfo implements Serializable {

    /**
     * 用户id
     */
    private long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码, 请不要存储明文密码
     */
    private String password;

    public UserInfo() {
    }

    public UserInfo(long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder
                .toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
