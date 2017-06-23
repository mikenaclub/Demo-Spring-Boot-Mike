package com.miniproject.com.miniproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by STR02119 on 6/21/2017.
 */

@Repository
public class Userrepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createuser(Usermodel user) {
        jdbcTemplate.update("INSERT INTO USER(name,password) VALUES (?,?)", user.getName(), user.getPassword());
    }
}
