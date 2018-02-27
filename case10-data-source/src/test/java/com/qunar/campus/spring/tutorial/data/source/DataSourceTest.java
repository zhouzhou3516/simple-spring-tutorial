package com.qunar.campus.spring.tutorial.data.source;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: DataSourceTest
 *
 * 从这里开始，我们使用spring测试框架
 *
 * 这一部分内容不多。简单来看，就是给运行的测试环境注入Spring Context
 * 使得我们更容易的测试spring中的bean
 *
 * @see http://docs.spring.io/spring/docs/current/spring-framework-reference/html/testing.html
 *
 * 注意在，练习中我们都使用h2这个数据库
 * 另外，一定注意ddl文件的编码是utf8。要不然会有奇怪的问题
 *
 * @author yushen.ma
 * @version 2015-03-22 22:36
 */
@Synopsis(name="data source test", difficulty = Difficulty.NORMAL)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:dao.xml")
public class DataSourceTest {

    @Resource
    DataSource dataSource;

    final private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void dataSourceTest() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement =
                    connection.prepareStatement("INSERT INTO `user` (`username`, `password`) VALUES (?, ?) ");
            preparedStatement.setString(1, "yushen.ma");
            preparedStatement.setString(2, DigestUtils.md5DigestAsHex("123321".getBytes()));
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("SELECT * FROM `user` where `username`=?");
            preparedStatement.setString(1, "yushen.ma");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Assert.assertEquals(1, id);
                Assert.assertEquals("yushen.ma", username);
                logger.info("id: {}, username: {}, password: {}", id, username, password);
            }
        } finally { //这段恶心的代码，将在我们讲了JDBC Template之后消失不见
            try {
                if (null != connection) {
                    connection.close();
                }
            } finally {
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
            }
        }
    }
}
