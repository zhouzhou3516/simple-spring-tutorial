package com.qunar.campus.spring.tiny.xml;

import org.apache.commons.lang3.StringUtils;

/**
 * Description: LoginService
 *
 * @author yushen.ma
 * @version 2015-03-20 10:12
 */
public class LoginService {

    private String username;

    private String password;

    private HelloWorldService welcome;

    public String login(String username, String password) {
        if (StringUtils.equals(this.username, username)
                && StringUtils.equals(this.password, password)) {
            return this.welcome.helloWorld();
        } else {
            return "forbidden";
        }
    }


}

