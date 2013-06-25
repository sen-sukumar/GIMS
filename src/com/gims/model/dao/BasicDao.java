package com.gims.model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @author sukumar sen
 */
public class BasicDao {
    static{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }
    protected Connection getConnection() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "gims", "gims");
        return conn;
    }
    protected void closeConnection(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {}
    }
    protected void closeStatement(Statement st){
        try{
            st.close();
        }
        catch(SQLException e){}
    }
}
