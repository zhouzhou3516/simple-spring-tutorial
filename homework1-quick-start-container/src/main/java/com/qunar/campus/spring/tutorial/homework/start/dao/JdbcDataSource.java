package com.qunar.campus.spring.tutorial.homework.start.dao;

import com.qunar.campus.spring.tutorial.homework.start.bean.UserInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * Created by liqingzhou on 17/8/7.
 */
public class JdbcDataSource {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getJdbcTemplate() {
        //此处没有实现连接池
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("tables.ddl")
                .build();
        return new JdbcTemplate(db);
    }


}
