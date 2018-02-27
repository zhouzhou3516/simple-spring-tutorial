package com.qunar.campus.spring.tutorial.homework3.service;

import com.qunar.campus.spring.tutorial.homework3.bean.Role;
import com.qunar.campus.spring.tutorial.homework3.bean.UserInfo;
import com.qunar.campus.spring.tutorial.homework3.bean.UserInfoVo;
import com.qunar.campus.spring.tutorial.homework3.bean.security.Subject;
import java.util.List;

/**
 * Description: UserManageService
 *
 * @author yushen.ma
 * @version 2015-03-30 10:10
 */
public interface UserManagerService {


    Subject getSubject(String username);
    /**
     * 添加用户
     *
     * 如果角色不存在就throw exception
     *
     * @param user 用户
     * @param role 角色
     */
    void addUser(UserInfo user, Role role);

    /**
     * 创建角色
     *
     * @param role 角色
     */
    void addRole(Role role);

    /**
     * 修改用户信息
     *
     * @param user 用户
     */
    void updateUser(UserInfo user);

    /**
     * 删除用户
     * Tips: 需要同时删除关联信息
     *
     * @param userId 用户
     */
    void deleteUser(long userId);

    /**
     * 用户登录
     * Tips: 这里的用户名是明文的，但是数据库里面需要是不可逆加密的，自行百度
     *
     * @param username 用户名
     * @param password 密码 ---> 这是明文的
     * @return 用户名和密码是否匹配
     */
    boolean login(String username, String password);

    /**
     * 查询角色
     *
     * @return list of roles
     */
    List<Role> listRoles();



    /**
     * 分页查询用户
     *
     * Tips: limit ((page-1) * pageSize) (page * pageSize)
     *
     * @param page 页数 >=1
     * @param pageSize 每一页大小
     * @return list of userInfo
     */
    List<UserInfoVo> listUsers(int page, int pageSize);

    /**
     * 查询某种类型的用户的数量
     *
     * @param role role
     * @return int
     */
    int userCount(Role role);

}
