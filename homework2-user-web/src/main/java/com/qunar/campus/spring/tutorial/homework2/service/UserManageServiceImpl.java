package com.qunar.campus.spring.tutorial.homework2.service;

import com.benmu.util.Safes;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.hash.Hashing;
import com.qunar.campus.spring.tutorial.homework2.bean.Role;
import com.qunar.campus.spring.tutorial.homework2.bean.RoleMapping;
import com.qunar.campus.spring.tutorial.homework2.bean.UserInfo;
import com.qunar.campus.spring.tutorial.homework2.bean.UserInfoVo;
import com.qunar.campus.spring.tutorial.homework2.dao.RoleDao;
import com.qunar.campus.spring.tutorial.homework2.dao.RoleMappingDao;
import com.qunar.campus.spring.tutorial.homework2.dao.UserDao;
import com.qunar.campus.spring.tutorial.homework2.util.adapter.UserInfoAdapter;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liqingzhou on 17/8/1.
 */
@Service
public class UserManageServiceImpl implements UserManagerService {

    @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleMappingDao roleMappingDao;

    /**
     *
     * The default behavior is rollback on unchecked exception.
     * unchecked exception = RuntimeException + Error
     *
     * @param user 用户
     * @param role 角色
     */
    @Override
    @Transactional
    public void addUser(UserInfo user, Role role) {
        Preconditions.checkArgument(role != null);
        //role 不存在，抛异常
        Role realRole = roleDao.selectRole(role.getId());
        Preconditions.checkArgument(realRole != null);
        userDao.addUser(user);
        RoleMapping mapping = new RoleMapping();
        mapping.setRoleId(role.getId());
        mapping.setUserId(user.getUserId());
        //Preconditions.checkArgument(false, "测了个是");
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
        UserInfo user = userDao.selectUserByName(username);
        if (user != null) {
            String salt = user.getSalt();
            String pwd = Hashing.md5().hashString(password + salt, Charsets.UTF_8).toString();


        }
        return false;


    }

    @Override
    public List<Role> listRoles() {

        return roleDao.selectAllRoles();
    }

    @Override
    public List<UserInfoVo> listUsers(int page, int pageSize) {
        Preconditions.checkArgument(page > 0 && pageSize > 0);
        int offset = (page - 1) * pageSize;

        return Safes.of(userDao.userPage(offset, pageSize)).stream()
                .map(UserInfoAdapter::adaptorToVo).collect(
                        Collectors.toList());
    }

    @Override
    public int userCount(Role role) {
        return roleMappingDao.countByRole(role);
    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public void setRoleMappingDao(
            RoleMappingDao roleMappingDao) {
        this.roleMappingDao = roleMappingDao;
    }
}
