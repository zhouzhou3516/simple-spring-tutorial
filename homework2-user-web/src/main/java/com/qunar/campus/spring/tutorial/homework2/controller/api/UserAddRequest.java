package com.qunar.campus.spring.tutorial.homework2.controller.api;

import com.qunar.campus.spring.tutorial.homework2.bean.Role;

/**
 * Description: UserAddRequest
 *
 * @author yushen.ma
 * @version 2015-04-04 21:37
 */
public class UserAddRequest {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码, 请不要存储明文密码, TODO 考虑一下密码应该怎么存
     *
     * salt+md5 加密，salt由系统随机生成并入库，并且只有系统知道
     */
    private String password;

    /**
     * 关联角色id
     */
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
