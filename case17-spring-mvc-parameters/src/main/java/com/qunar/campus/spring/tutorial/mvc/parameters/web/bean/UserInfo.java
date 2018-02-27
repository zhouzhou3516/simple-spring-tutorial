package com.qunar.campus.spring.tutorial.mvc.parameters.web.bean;

import java.io.Serializable;

/**
 * Description: UserInfo
 *
 * @author yushen.ma
 * @version 2015-03-28 18:17
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 8583391180630280723L;

    private long userId;

    private String username;

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
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
