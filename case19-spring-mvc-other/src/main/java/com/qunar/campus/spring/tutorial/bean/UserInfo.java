package com.qunar.campus.spring.tutorial.bean;

import java.io.Serializable;

/**
 * Description: UserInfo
 *
 * @author yushen.ma
 * @version 2015-04-02 00:39
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 2620032660446040726L;

    private long userId;

    private String username;

    private String password;

    public UserInfo() { }

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
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
