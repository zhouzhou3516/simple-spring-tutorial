package com.qunar.campus.spring.tutorial.homework.start.service;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
import com.qunar.campus.spring.tutorial.homework.start.bean.RoleMapping;
import com.qunar.campus.spring.tutorial.homework.start.bean.UserInfo;
import com.qunar.campus.spring.tutorial.homework.start.dao.RoleDao;
import com.qunar.campus.spring.tutorial.homework.start.dao.RoleMappingDao;
import com.qunar.campus.spring.tutorial.homework.start.dao.UserDao;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liqingzhou on 17/8/1.
 */
public class UserManageServiceImpl implements UserManageService {

    private UserDao userDao;
    private RoleDao roleDao;
    private RoleMappingDao roleMappingDao;


    public UserManageServiceImpl() {
    }

    @Override
    public void addUser(UserInfo user, Role role) {
        Preconditions.checkArgument(role != null);
        //role 不存在，抛异常
        Role realRole = roleDao.selectRole(role.getId());
        Preconditions.checkArgument(realRole != null);
        userDao.addUser(user);
        RoleMapping mapping = new RoleMapping();
        mapping.setUserId(user.getUserId());
        mapping.setRoleId(role.getId());
        roleMappingDao.addRoleMapping(mapping);

    }

    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    @Override
    public void updateUser(UserInfo user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        userDao.deleteUser(userId);
        roleMappingDao.delete(userId);
    }

    @Override
    public boolean login(String username, String password) {
        String pwd = Hashing.md5().hashString(password, Charsets.UTF_8).toString();
        UserInfo userInfo = userDao.selectUser(username, pwd);
        return userInfo != null;

    }

    @Override
    public List<Role> listRoles() {

        return roleDao.selectAllRoles();
    }

    @Override
    public List<UserInfo> listUsers(int page, int pageSize) {
        Preconditions.checkArgument(page > 0 && pageSize > 0);
        int offset = (page - 1) * pageSize;
        return userDao.userPage(offset, pageSize);
    }

    @Override
    public int userCount(Role role) {
        return roleMappingDao.countByRole(role);
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleMappingDao getRoleMappingDao() {
        return roleMappingDao;
    }

    public void setRoleMappingDao(
            RoleMappingDao roleMappingDao) {
        this.roleMappingDao = roleMappingDao;
    }
}
