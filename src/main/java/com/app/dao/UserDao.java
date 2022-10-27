package com.app.dao;

import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUsers(){
        RowMapper<User> rowMapper = (rs, rowNumber)-> mapUser(rs);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }


    private User mapUser(ResultSet result) throws SQLException {
      User user = new User();

      user.setId(result.getLong("id"));
      user.setFirstName(result.getString("first_name"));
      user.setLastName(result.getString("last_name"));
      user.seteMail(result.getString("email"));
      user.setPhoneNumber(result.getString("phone"));

      return user;
    }


    public void saveUser (User user){
        jdbcTemplate.update("INSERT INTO users (first_name, last_name, email, phone, password)VALUES (?, ?, ?, ?, '123')",
                user.getFirstName(),user.getLastName(), user.geteMail(), user.getPhoneNumber());

    }
}
