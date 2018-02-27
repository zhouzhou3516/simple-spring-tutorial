package com.qunar.campus.spring.tutorial.homework2.dao;

import com.google.common.collect.Lists;
import com.qunar.campus.spring.tutorial.homework2.bean.Role;
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
public class RoleDao {

    JdbcTemplate jdbcTemplate;
    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int addRole(final Role role) {

        final String INSERT_SQL = "INSERT INTO `role` (name) values(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection
                            .prepareStatement(INSERT_SQL, new String[]{"id"});
                    ps.setString(1, role.getName());
                    return ps;
                },
                keyHolder);
        role.setId(keyHolder.getKey().intValue());
        return keyHolder.getKey().intValue();

    }

    public Role selectRole(int id) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "select `id`, `name` from `role` where `id` = ?",
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Role role = new Role();
                        role.setId(rs.getInt("id"));
                        role.setName(rs.getString("name"));
                        return role;
                    });
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }
    public Role selectRoleByName(String name) {
        try {
            return this.jdbcTemplate.queryForObject(
                    "select `id`, `name` from `role` where `name` = ?",
                    new Object[]{name},
                    (rs, rowNum) -> {
                        Role role = new Role();
                        role.setId(rs.getInt("id"));
                        role.setName(rs.getString("name"));
                        return role;
                    });
        } catch (EmptyResultDataAccessException e) {

            return null;
        }
    }

    public List<Role> selectAllRoles() {
        try {
            return this.jdbcTemplate.query(
                    "select `id`, `name` from `role`",
                    new RowMapper<Role>() {
                        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Role role = new Role();
                            role.setId(rs.getInt("id"));
                            role.setName(rs.getString("name"));
                            return role;
                        }
                    });
        } catch (EmptyResultDataAccessException e) {

            return Lists.newArrayList();
        }
    }


}
