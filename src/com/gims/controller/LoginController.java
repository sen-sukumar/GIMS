package com.gims.controller;
import com.gims.view.GimsStart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * @author sukumar sen
 */
public class LoginController {
    GimsStart start;
    public LoginController(GimsStart start){
        this.start = start;
    }
    public void login(String user, String pwd, String type) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "gims", "gims");
            if (conn != null) {
                Statement st = conn.createStatement();
                String sql = "select * from gims_user where user_id='" + user + "' and password='" 
                            + pwd + "' and user_type='" + type + "'";
                System.out.println(sql);
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()){
                    start.viewLoggedIn(type);
                }
                else{
                    start.viewLoginFailed(type);
                }
            } else {
                start.viewConnectionFailed(type);
            }
        } catch (Exception ex) {
            start.viewConnectionFailed(type);
        }
    }
    public void loginCancel() {
    }
}