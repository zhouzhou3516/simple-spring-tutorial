package com.qunar.campus.spring.tutorial.homework3.bean.security;

/**
 * Created by liqingzhou on 17/8/7.
 */
public class UserLoginRequest {

    private String username;
    private String password;

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
}
