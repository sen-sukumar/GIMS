package com.gims.controller;
import com.gims.model.dao.CategoryDao;
import com.gims.model.dao.DaoException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
/**
 * @author sukumar sen
 */
public class CategoryController {
    public CategoryController() {
    }
    public boolean addCategory(String name){
        try{
            new CategoryDao().insert(name);
        }
        catch(SQLException e){
            return false;
        }
        return true;
    }
    public boolean editCategory(String name, String oldName){
        try{
            new CategoryDao().update(name, oldName);
        }
        catch(SQLException | DaoException e){
            return false;
        }
        return true;
    }
    public boolean removeCategory(String name){
        try{
            new CategoryDao().delete(name);
        }
        catch(SQLException | DaoException e){
            return false;
        }
        return true;
    }
    public boolean loadCategories(List<String> categoryList){
        try{
            new CategoryDao().readAll(categoryList);
            Collections.sort(categoryList);
        }
        catch(SQLException e){
            return false;
        }
        return true;
    }
}
