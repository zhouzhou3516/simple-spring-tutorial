package com.qunar.campus.spring.tutorial.transaction.aop.dao;

import com.qunar.campus.spring.tutorial.bean.Money;
import com.qunar.campus.spring.tutorial.transaction.aop.bean.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description: JDBCTemplateSynopsis
 *
 * 这是一个银行账户的 DAO
 *
 * @author yushen.ma
 * @version 2015-03-24 23:38
 */
@Repository
public class AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Account find(final int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM `account` WHERE `id`=?",
                new Object[] {id},
                new RowMapper<Account>() {
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(rs.getInt("id"),
                                rs.getInt("user_id"),
                                Money.of(rs.getBigDecimal("balance")));
                    }
                });
    }

    /**
     * 创建账户
     * @param account 账户
     */
    public void create(final Account account) {
        final String INSERT_SQL = "INSERT INTO `account` (`user_id`, `balance`) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
                        ps.setInt(1, account.getUserId());
                        ps.setBigDecimal(2, account.getBalance().toStoreDecimal());
                        return ps;
                    }
                },
                keyHolder);
        account.setId(keyHolder.getKey().intValue());
    }

    /**
     * 更新账户
     * @param account 账户
     */
    public void update(final Account account) {
        this.jdbcTemplate.update(
                "UPDATE `account` SET `user_id` = ? , `balance` = ? WHERE `id` = ?",
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        ps.setInt(1, account.getUserId());
                        ps.setBigDecimal(2, account.getBalance().toStoreDecimal());
                        ps.setInt(3, account.getId());
                    }
                });
    }

}
