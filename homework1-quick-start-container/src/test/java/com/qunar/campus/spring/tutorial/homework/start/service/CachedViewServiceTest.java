package com.qunar.campus.spring.tutorial.homework.start.service;

import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
import com.qunar.campus.spring.tutorial.homework.start.container.context.MyApplicationContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liqingzhou on 17/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao.xml")
public class CachedViewServiceTest {

    Logger logger = LoggerFactory.getLogger(getClass());


    private CachedViewService cachedViewService;

    @Before
    public void clearAndInit() {
        MyApplicationContext myApplicationContext = new MyApplicationContext("my-bean.xml");

        cachedViewService = myApplicationContext.getBean("cachedViewService");

        //数据初始化放入tables.ddl

    }

    @Test
    public void userCount() throws Exception {
        int studentCount = cachedViewService.userCount(new Role(1, "学生"));
        int teacherCount = cachedViewService.userCount(new Role(2, "老师"));
        int zhurenCount = cachedViewService.userCount(new Role(3, "主任"));
        int xiaozhangCount = cachedViewService.userCount(new Role(4, "校长"));

        Assert.assertEquals(studentCount, 5);
        Assert.assertEquals(teacherCount, 3);
        Assert.assertEquals(zhurenCount, 2);
        Assert.assertEquals(xiaozhangCount, 1);
    }


}
