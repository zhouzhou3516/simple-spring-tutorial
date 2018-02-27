package com.qunar.campus.spring.tutorial.homework.start.dao;

import com.google.common.base.Preconditions;
import com.qunar.campus.spring.tutorial.homework.start.bean.Role;
import com.qunar.campus.spring.tutorial.homework.start.bean.RoleMapping;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by liqingzhou on 17/8/1.
 */
public class RoleMappingDao {

    JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addRoleMapping(final RoleMapping mapping) {
        final String INSERT_SQL = "INSERT INTO `role_mapping` (user_id,role_id) values(?,?)";
        this.jdbcTemplate.update(
                INSERT_SQL,
                mapping.getUserId(), mapping.getRoleId());

    }

    public int delete(long userId) {
        Preconditions.checkArgument(userId > 0L);
        return this.jdbcTemplate.update("DELETE FROM `role_mapping` where user_id = ?", userId);
    }

    public int countByRole(Role role) {
        Preconditions.checkArgument(role.getId() > 0);
        return this.jdbcTemplate
                .queryForObject("SELECT count(1) from `role_mapping` where role_id=?",
                        new Object[]{role.getId()}, Integer.class);
    }
}
