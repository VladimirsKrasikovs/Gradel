package com.app.services;

import com.app.dao.CatalogDao;
import com.app.model.CatalogItem;
import com.app.model.Category;
import com.app.model.SubCategory;
import org.apache.el.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CatalogDao catalogDao;

    public List<Category> getCategories(){
        return catalogDao.getCategories();
    }

    public void storeCategory(Category category){
        //Validatiion
        catalogDao.storeCategory(category);
    }

     public List<SubCategory> getSubcategories(){
        return catalogDao.getSubcategories();
     }

    public List<SubCategory> getSubcategories(long id)
    {
        return catalogDao.getSubcategories(id);
    }

     public void storeSubcategory (SubCategory subCategory){
        catalogDao.storeSubcategory(subCategory);
     }

     public List<CatalogItem> getItems (){
        return catalogDao.getItems();
     }

     public void storeItem(CatalogItem item){
         //Validation
         String newPrice = item.getPrice().toString().replace(',', '.');
         item.setPrice(new BigDecimal(newPrice));
         catalogDao.storeItem(item);
     }
}
