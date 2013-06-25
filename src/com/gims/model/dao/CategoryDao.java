package com.gims.model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
/**
 * @author sukumar sen
 */
public class CategoryDao extends BasicDao {
    public static final String INSERT_SQL = "insert into category(name) values(?)";
    public static final String UPDATE_SQL = "update category set name = ? where name = ?";
    public static final String DELETE_SQL = "delete category where name = ?";
    public static final String SELECT_ALL_SQL = "select * from category";
    public void insert(String name) throws SQLException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            try {
                ps.setString(1, name);
                ps.executeUpdate();
            }
            finally{
                closeStatement(ps);
            }
        }
        finally{
            closeConnection(con);
        }
    }
    public void update(String name, String oldName) throws SQLException,DaoException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(UPDATE_SQL);
            try{
                ps.setString(1, name);
                ps.setString(2, oldName);
                if(ps.executeUpdate() != 1){
                    throw new DaoException("update no match");
                }
            }
            finally{
                closeStatement(ps);
            }
        }
        finally{
            closeConnection(con);
        }
    }
    public void delete(String name) throws SQLException, DaoException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(DELETE_SQL);
            try{
                ps.setString(1, name);
                if(ps.executeUpdate() != 1){
                    throw new DaoException("delete no match");
                }
            }
            finally{
                closeStatement(ps);
            }
        }
        finally{
            closeConnection(con);
        }
    } 
    /*public University read(University university){
        
    }*/
    public void readAll(List<String> categoryList) throws SQLException{
        Connection con = getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_SQL);
            try{
                while(rs.next()){
                    String name = rs.getString("name");
                    categoryList.add(name);
                }
            }
            finally{
                closeStatement(st);
            }
        }
        finally{
            closeConnection(con);
        }
    }
}
