package com.app.services;

import com.app.dao.CatalogDao;
import com.app.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
