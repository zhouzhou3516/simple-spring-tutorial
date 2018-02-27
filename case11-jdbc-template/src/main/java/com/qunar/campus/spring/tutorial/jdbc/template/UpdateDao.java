package com.qunar.campus.spring.tutorial.jdbc.template;

import com.google.common.base.Preconditions;
import com.qunar.campus.spring.tutorial.Difficulty;
import com.qunar.campus.spring.tutorial.Synopsis;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Description: UpdateDao
 *
 * update 包括 删，更新，插入
 *
 * @see http://docs.spring.io/spring/docs/4.1.5.RELEASE/spring-framework-reference/html/jdbc.html#jdbc-datasource
 *
 * @author yushen.ma
 * @version 2015-03-23 00:13
 */
@Synopsis(difficulty = Difficulty.EASY, name = "spring jdbc template update")
@Repository
public class UpdateDao {

    JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(javax.sql.DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 添加一个用户
     * @param user user
     */
    public void addUser(User user) {
        this.jdbcTemplate.update(
                "INSERT INTO `user` (`username`, `password`) VALUES (?, ?)",
                user.getUserName(), user.getPassword());
    }

    /**
     * 添加一个用户并且返回插入的id， 以及回写id到对应的User实体类中
     *
     * 这个才是一个更常见的插入操作
     *
     * @param user user
     * @return int
     */
    public long addUserBetter(final User user) {
        final String INSERT_SQL = "INSERT INTO `user` (username, password) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
                        ps.setString(1, user.getUserName());
                        ps.setString(2, user.getPassword());
                        return ps;
                    }
                },
                keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }

    /**
     * 更新某个用户的名字
     * @param user user
     */
    public int update(User user) {
        Preconditions.checkArgument(user.getId() > 0L);
        return this.jdbcTemplate.update(
                "UPDATE `user` SET `username` = ?, `password` = ? WHERE `id` = ?",
                user.getUserName(), user.getPassword(), user.getId());
    }

    /**
     * 删除某个用户
     * @param user user
     */
    public int delete(User user) {
        Preconditions.checkArgument(user.getId() > 0L);
        return this.jdbcTemplate.update(
                "DELETE FROM `user`where id = ?",
                user.getId());
    }

    // 其他操作你们自己百度吧。我打字也很累 ..
    // 还有一些什么simple insert之类的东西，看看就行了。想想别人为什么这么设计API
}
