package com.app.dao;

import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CatalogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Category> getCategories(){
        RowMapper<Category> rowMapper = (rs, rowNumber) -> mapCategory(rs);
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
    public List<SubCategory> getSubcategories(){
        RowMapper<SubCategory> rowMapper = (rs, rowNumber) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id , s.name AS s_name , c.id AS c_id,  c.name AS c_name " +
                "FROM subcategory s " +
                "INNER JOIN categories c ON s.category_id = c.id", rowMapper);
    }

    public List<SubCategory> getSubcategories(long categoryId){
        RowMapper<SubCategory> rowMapper = (rs, rowNumber) -> mapSubcategory(rs);
        return jdbcTemplate.query("SELECT s.id AS s_id , s.name AS s_name , c.id AS c_id,  c.name AS c_name " +
                "FROM subcategory s WHERE s.category_id = ? " +
                "INNER JOIN categories c ON s.category_id = c.id", rowMapper, categoryId);
    }

    private SubCategory mapSubcategory(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong("c_id"));
        category.setName(rs.getString("c_name"));

        SubCategory subCategory = new SubCategory();
        subCategory.setCategory(category);
        subCategory.setId(rs.getLong("s_id"));
        subCategory.setName(rs.getString("s_name"));

        return subCategory;
    }

    public void storeSubcategory(SubCategory subCategory){
        jdbcTemplate.update("INSERT INTO subcategory (name, category_id) VALUES (?, ?)",
            subCategory.getName(),subCategory.getCategory().getId());
    }

    public List<CatalogItem> getItems(){
        RowMapper<CatalogItem> rowMapper = (rs, i) -> mapItem(rs);
        return jdbcTemplate.query("SELECT * FROM catalog", rowMapper);
    }

    private CatalogItem mapItem(ResultSet rs) throws SQLException {
        CatalogItem item = new CatalogItem();

        item.setId(rs.getLong("id"));
        item.setSubcategoryId(rs.getLong("subcategory_id"));
        item.setName(rs.getString("name"));
        item.setDescription(rs.getString("description"));
        item.setPrice(new BigDecimal(rs.getString("pricae")));
        item.setBrandId(rs.getLong("brand_id"));
        item.setImage(rs.getString("image"));

        return item;

    }

    public void storeItem (CatalogItem item){
        jdbcTemplate.update("INSERT INTO catalog (subcategory_id, name, description, pricae) VALUES (?, ?, ?, ?)",
        item.getSubcategoryId(), item.getName(), item.getDescription(), item.getPrice()) ;

    }

}
