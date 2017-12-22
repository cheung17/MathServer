package com.ztx.math.dao;

import com.ztx.math.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserEntity getUserById(int id) {
        String sql = "select * from spring_mvc_user where id = ? ";
        final UserEntity user = new UserEntity();
        jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setId(resultSet.getInt("id"));
                user.setLastName(resultSet.getString("lastName"));
                user.setFirstName(resultSet.getString("FirstName"));
                user.setPassword("password");
            }
        });
        return user;
    }
}
