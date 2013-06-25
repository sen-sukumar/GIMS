package com.gims.model.dao;
import com.gims.model.dto.University;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
/**
 * @author sukumar sen
 */
public class UniversityDao extends BasicDao {
    public static final String INSERT_SQL = "insert into university(name,address,phone) values(?,?,?)";
    public static final String UPDATE_SQL = "update university set name = ? where name = ?";
    public static final String DELETE_SQL = "delete university where name = ?";
    public static final String SELECT_ALL_SQL = "select * from university";
    public static final String SELECT_SQL = "select * from university where name = ?";
    public void insert(University university) throws SQLException{
        Connection con = getConnection();
        con.setAutoCommit(false);
        try{
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            try {
                ps.setString(1, university.getName());
                if (university.getAddress() != null) {
                    ps.setString(2, university.getAddress());
                } else {
                    ps.setNull(2, Types.VARCHAR);
                }
                if (university.getPhone() != null) {
                    ps.setString(3, university.getPhone());
                } else {
                    ps.setNull(3, Types.VARCHAR);
                }
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
    public void readAll(List<University> universityList) throws SQLException{
        Connection con = getConnection();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_SQL);
            try{
                while(rs.next()){
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    if (rs.wasNull()) address = null;
                    String phone = rs.getString("phone");
                    if (rs.wasNull()) phone = null;
                    University university = new University(name, address, phone);
                    universityList.add(university);
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
