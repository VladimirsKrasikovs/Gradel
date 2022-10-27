package com.app.dao;

import com.app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories(){
        RowMapper<Category> rowMapper = (rs, rowNUmber) -> mapCategory(rs);
        return jdbcTemplate.query("SELECT * FROM categories", rowMapper);
    }


    private Category mapCategory(ResultSet rs) throws SQLException {
        Category category =new Category();

        category.setId(rs.getLong("id"));
        category.setName(rs.getString("name"));

        return category;
    }

    public void storeCategory(Category category){
        jdbcTemplate.update("INSERT INTO categories (name) VALUES (?)", category.getName());

    }

}
