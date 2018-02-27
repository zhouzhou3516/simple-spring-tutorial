package com.qunar.campus.spring.tutorial.homework.start.service;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.qunar.campus.spring.tutorial.homework.start.service.handler.Handler;
import java.util.List;
import javax.annotation.Resource;
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
public class AnnotationTest {

    private final String password = Hashing.md5().hashString("123456", Charsets.UTF_8).toString();
    @Resource
    UserManageService userManageService;
    @Resource
    CachedViewService cachedViewService;
    @Resource
    private List<Handler> handlerList;

    @Before
    public void clearAndInit() {

    }

    @Test
    public void testBeanOrder() throws Exception {
        handlerList.forEach(handler -> System.out.println(handler.getClass()));
        System.out.println("handlerList = " + handlerList);
    }


}
