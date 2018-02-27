package com.qunar.campus.spring.tutorial.service;

import com.qunar.campus.spring.tutorial.bean.UserInfo;
import org.springframework.stereotype.Service;

/**
 * Description: UserService
 *
 * @author yushen.ma
 * @version 2015-04-02 00:39
 */
@Service
public class UserService {

    public UserInfo check(String username, String password) {
        // always return ok
        return new UserInfo(1, "admin", "xxx");
    }

}
