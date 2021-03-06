package com.qunar.campus.spring.tutorial.homework.start.service;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
import com.qunar.campus.spring.tutorial.homework.start.bean.UserInfo;
import com.qunar.campus.spring.tutorial.homework.start.dao.RoleDao;
import com.qunar.campus.spring.tutorial.homework.start.dao.RoleMappingDao;
import com.qunar.campus.spring.tutorial.homework.start.dao.UserDao;
import java.util.List;
import javax.annotation.Resource;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liqingzhou on 17/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class UserManageServiceTest {

    private final String password = Hashing.md5().hashString("123456", Charsets.UTF_8).toString();
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    UserDao userDao;
    @Resource
    RoleDao roleDao;
    @Resource
    RoleMappingDao roleMappingDao;
    @Resource
    UserManageService userManageService;

    @Before
    public void clearAndInit() {
        //初始化3个角色
        userManageService.addRole(new Role(0, "学生"));
        userManageService.addRole(new Role(0, "老师"));
        userManageService.addRole(new Role(0, "主任"));

        //初始化4个user
        userManageService.addUser(new UserInfo(0L, "student1", password), new Role(1, "学生"));
        userManageService.addUser(new UserInfo(0L, "student2", password), new Role(1, "学生"));

        userManageService.addUser(new UserInfo(0L, "teacher1", password), new Role(2, "老师"));
        userManageService.addUser(new UserInfo(0L, "teacher2", password), new Role(2, "老师"));
    }


    @Test
    public void addUser() throws Exception {
        UserInfo userInfo = new UserInfo(0L, "student6", password);
        Role role = new Role(1, "");
        userManageService.addUser(userInfo, role);
        logger.info("new added user:{}", userInfo);
        Assert.assertEquals(userInfo.getUserId() > 0, true);

    }

    @Test
    public void addRole() throws Exception {
        Role role = new Role(0, "校长");
        userManageService.addRole(role);
        Role xiaozhang = roleDao.selectRoleByName("校长");
        logger.info("new role:{}", xiaozhang);
        Assert.assertEquals(xiaozhang.getName(), "校长");

    }

    @Test
    public void updateUser() throws Exception {
        UserInfo userInfo = new UserInfo(4L, "student6NewName", password);
        userManageService.updateUser(userInfo);
        List<UserInfo> userInfos = userManageService.listUsers(1, 5);
        Assert.assertEquals(userInfos != null, true);
        userInfos.forEach(user -> {
            if (user.getUserId() == 4) {
                Assert.assertEquals(user.getUsername(), "student6NewName");
            }
        });

    }

    @Test
    public void deleteUser() throws Exception {
        UserInfo before = userDao.selectUserById(1);
        Assert.assertEquals(before.getUserId(), 1);
        userManageService.deleteUser(1);
        UserInfo after = userDao.selectUserById(1);
        Assert.assertEquals(after, null);


    }

    @Test
    public void login() throws Exception {

        UserInfo userInfo = new UserInfo(0L, "student7", password);
        Role role = new Role(1, "");
        userManageService.addUser(userInfo, role);

        boolean ret = userManageService.login("student7", "123456");
        Assert.assertEquals(ret, true);
        boolean ret2 = userManageService.login("aljdflajf", "123456");
        Assert.assertEquals(ret2, false);
        boolean ret3 = userManageService.login("student7", "asdfasfdas");
        Assert.assertEquals(ret3, false);


    }

    @Test
    public void listRoles() throws Exception {
        List<Role> list = userManageService.listRoles();
        Assert.assertEquals(list != null, true);
        logger.info("size:{}", list.size());
        Assert.assertEquals(list.size() > 0, true);
    }

    @Test
    public void listUsers() throws Exception {
        List<UserInfo> list = userManageService.listUsers(1, 10);

        Assert.assertEquals(list != null, true);
        for (UserInfo userInfo : list) {

            logger.info("user size:{}", userInfo);
        }
        Assert.assertEquals(list.size() > 0, true);
    }

    @Test
    public void userCount() throws Exception {

        UserInfo userInfo = new UserInfo(0L, "student6", password);
        Role role = new Role(1, "");
        userManageService.addUser(userInfo, role);
        logger.info("userInfo :{}", userInfo);
        int count = userManageService.userCount(role);
        Assert.assertEquals(count, 3);

        int count2 = userManageService.userCount(new Role(2, "老师"));
        Assert.assertEquals(count2, 2);

    }


}
