package com.qunar.campus.spring.tutorial.homework.start.dao;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.homework.start.bean.UserInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * Created by liqingzhou on 17/8/1.
 */
@Repository
public class UserDao {

    JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public long addUser(final UserInfo user) {
        final String INSERT_SQL = "INSERT INTO `user` (username, password) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection
                            .prepareStatement(INSERT_SQL, new String[]{"id"});
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    return ps;
                },
                keyHolder);
        user.setUserId(keyHolder.getKey().longValue());
        return keyHolder.getKey().longValue();
    }

    public UserInfo selectUserById(long id) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "select `id`, `username`, `password` from `user` where `id` = ? ",
                    new Object[]{id},
                    new userMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserInfo selectUserByName(String name) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "select `id`, `username`, `password` from `user` where `name` = ? ",
                    new Object[]{name},
                    new userMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserInfo selectUser(String username, String password) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "select `id`, `username`, `password` from `user` where `username` = ? AND `password` = ?",
                    new Object[]{username, password},
                    new userMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    public List<UserInfo> userPage(int offset, int pageSize) {
        try {
            return this.jdbcTemplate.query(
                    "SELECT `id`, `username`, `password` FROM `user`  LIMIT ?,?",
                    new Object[]{offset, pageSize},
                    new userMapper());
        } catch (EmptyResultDataAccessException e) {
            return Lists.newArrayList();
        }
    }


    public int updateUser(UserInfo user) {
        Preconditions.checkArgument(user.getUserId() > 0L);
        return this.jdbcTemplate
                .update("UPDATE `user` SET `username` = ?, `password` = ? WHERE `id` = ?",
                        new Object[]{user.getUsername(), user.getPassword(), user.getUserId()});
    }

    public int deleteUser(long userId) {

        Preconditions.checkArgument(userId > 0L);
        return this.jdbcTemplate.update("DELETE FROM `user`where id = ?", userId);
    }

    public static class userMapper implements RowMapper<UserInfo> {

        @Override
        public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserInfo user = new UserInfo();
            user.setUserId(rs.getLong("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    }
}
