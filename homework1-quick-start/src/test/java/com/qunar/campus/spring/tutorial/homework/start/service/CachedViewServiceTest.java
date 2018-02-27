package com.qunar.campus.spring.tutorial.homework.start.service;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
import com.qunar.campus.spring.tutorial.homework.start.bean.UserInfo;
import javax.annotation.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liqingzhou on 17/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CachedViewServiceTest {

    private final String password = Hashing.md5().hashString("123456", Charsets.UTF_8).toString();
    @Resource
    UserManageService userManageService;
    @Resource
    CachedViewService cachedViewService;

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
        userManageService.addUser(new UserInfo(0L, "teacher3", password), new Role(2, "老师"));

        userManageService.addUser(new UserInfo(0L, "teacher1", password), new Role(3, "主任"));
        userManageService.addUser(new UserInfo(0L, "teacher2", password), new Role(3, "主任"));
        userManageService.addUser(new UserInfo(0L, "teacher3", password), new Role(3, "主任"));
        userManageService.addUser(new UserInfo(0L, "teacher4", password), new Role(3, "主任"));
    }

    @Test
    public void userCount() throws Exception {
        int studentCount = cachedViewService.userCount( new Role(1, "学生"));
        int teacherCount = cachedViewService.userCount( new Role(2, "学生"));
        int zhurenCount = cachedViewService.userCount( new Role(3, "学生"));

        Assert.assertEquals(studentCount,2);
        Assert.assertEquals(teacherCount,3);
        Assert.assertEquals(zhurenCount,4);
    }


}
