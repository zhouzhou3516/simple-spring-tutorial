package com.qunar.campus.spring.tutorial.scope.thread.safe;

import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.Serializable;

/**
 * Description: User
 *
 * @author yushen.ma
 * @version 2015-04-03 11:31
 */
public class User implements Serializable {

    private static final long serialVersionUID = -7421458111301967453L;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
