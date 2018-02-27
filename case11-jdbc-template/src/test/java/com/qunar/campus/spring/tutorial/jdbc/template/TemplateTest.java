package com.qunar.campus.spring.tutorial.jdbc.template;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description: TemplateTest
 *
 * @author yushen.ma
 * @version 2015-03-23 00:27
 */
@Synopsis(name="data source test", difficulty = Difficulty.NORMAL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao.xml")
public class TemplateTest {

    @Resource
    SelectDao selectDao;

    @Resource
    UpdateDao updateDao;

    private final String password = "thisIsPassword";

    @Before
    public void clearAndInit() {
        updateDao.addUser(new User(0L, "admin1", password));
        updateDao.addUser(new User(0L, "admin2", password));
        updateDao.addUser(new User(0L, "admin3", password));
    }

    @Test
    public void testSelect() {
        Assert.assertEquals(3, selectDao.selectCount());
        Assert.assertEquals(new User(1, "admin1", password), selectDao.selectUser(1));
        Assert.assertEquals(3, selectDao.selectAllUser().size());
        Assert.assertEquals("admin1", selectDao.selectUserName(1));
        Assert.assertEquals(4, updateDao.addUserBetter(new User(0, "admin4", password)));
        Assert.assertEquals(1, updateDao.delete(selectDao.selectUser(1)));
        User user = selectDao.selectUser(2);
        user.setUserName("updated");
        Assert.assertEquals(1, updateDao.update(user));

        user = selectDao.selectUser(2);
        Assert.assertEquals("updated", user.getUserName());
    }

}
