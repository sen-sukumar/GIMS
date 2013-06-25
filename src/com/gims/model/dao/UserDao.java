package com.gims.model.dao;

import static com.gims.model.dao.CategoryDao.INSERT_SQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author sukumar sen
 */
public class UserDao extends BasicDao{
    public static final String INSERT_SQL = "insert into gims_user(user_id,password,user_type) values(?,?,?)";
    public void insert(String user_id, String password, String user_type) throws SQLException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            try {
                ps.setString(1, user_id);
                ps.setString(2, password);
                ps.setString(3, user_type);
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
}
