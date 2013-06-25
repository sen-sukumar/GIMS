package com.gims.controller;

import com.gims.model.dao.UserDao;
import java.sql.SQLException;

/**
 * @author sukumar sen
 */
public class UserController {
    public UserController() {
    }
    public boolean createUser(String user,String password,String type){
        try{
            new UserDao().insert(user, password, type);
        }
        catch(SQLException e){
            return false;
        }
        return true;
    }
}
