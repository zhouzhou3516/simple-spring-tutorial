package com.qunar.campus.spring.tutorial.jdbc.template;

import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Description: SelectDao
 *
 * @see http://docs.spring.io/spring/docs/4.1.5.RELEASE/spring-framework-reference/html/jdbc.html#jdbc-datasource
 *
 * @author yushen.ma
 * @version 2015-03-23 00:00
 */
@Repository
@Synopsis(difficulty = Difficulty.EASY, name = "spring jdbc template select")
public class SelectDao {

    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 查询用户数量
     * @return int 用户数量
     */
    public int selectCount() {
        //count(*) 和 count(1)有什么不同吗？
        return jdbcTemplate.queryForObject("select count(1) from `user`", Integer.class);
    }

    /**
     * 根据用户id查询用户名字
     * @param id id
     * @return String 用户名字
     */
    public String selectUserName(long id) {
       return this.jdbcTemplate.queryForObject(
                "select `username` from `user` where id = ?",
                new Object[]{id}, String.class);
    }

    /**
     * 查询一个用户实体
     * 这个过程就叫对象关系映射--> ORM
     *
     * 因为我们的数据库是关系性的，但是对象呢，又是一个多级结构...
     * 如何把关系型的数据，拿到java中可以用呢。恩 听起来还蛮复杂的..
     *
     * @see http://en.wikipedia.org/wiki/Object-relational_mapping
     * 之后你们学的mybatis, 之类的框架都是做这件事情，只是他们抽象的程度不同 :) 了解这一点就ok
     *
     * @param id int id
     * @return String name of the user
     */
    public User selectUser(long id) {
        return this.jdbcTemplate.queryForObject(
                "select `id`, `username`, `password` from `user` where `id` = ?",
                new Object[]{id},
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setUserName(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
    }

    /**
     * 查询所有用户
     * @return a list of user
     */
    public List<User> selectAllUser() {
        return this.jdbcTemplate.query(
                "SELECT `id`, `username`, `password` FROM `user`",
                new RowMapper<User>() {
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setUserName(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        return user;
                    }
                });
    }

}
