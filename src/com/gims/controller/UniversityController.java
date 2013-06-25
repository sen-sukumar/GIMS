package com.gims.controller;
import com.gims.model.dao.DaoException;
import com.gims.model.dao.UniversityDao;
import com.gims.model.dto.University;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
/**
 * @author sukumar sen
 */
public class UniversityController {
    public UniversityController() {
    }
    public boolean addUniversity(String name, String address, String phone){
        University university = new University(name, address, phone);
        try{
            new UniversityDao().insert(university);
        }
        catch(SQLException e){
            return false;
        }
        return true;
    }
    public boolean editUniversity(String name, String oldName){
        try{
            new UniversityDao().update(name, oldName);
        }
        catch(SQLException | DaoException e){
            return false;
        }
        return true;
    }
    public boolean removeUniversity(String name){
        try{
            new UniversityDao().delete(name);
        }
        catch(SQLException | DaoException e){
            return false;
        }
        return true;
    }
    public boolean loadUniversities(List<University> universityList){
        try{
            new UniversityDao().readAll(universityList);
            Collections.sort(universityList);
        }
        catch(SQLException e){
            return false;
        }
        return true;
    }
}
