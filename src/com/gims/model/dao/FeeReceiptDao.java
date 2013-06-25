package com.gims.model.dao;

import com.gims.model.dto.FeeReceipt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author sukumar sen
 */
public class FeeReceiptDao extends BasicDao{
    public static final String INSERT_FEERECEIPT = "insert into fee_receipt(receipt_no, receipt_date, "
            + "admission_id, amount, detail, fee_type) values(?,?,?,?,?,?)";
    public void insertReceipt(FeeReceipt feeReceipt)throws SQLException, DaoException{
        Connection con = getConnection();
        try{
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            PreparedStatement psS = con.prepareStatement("select receipt_no from fee_receipt where receipt_no=?");
            try{
                psS.setString(1, feeReceipt.getReceiptNo());
                ResultSet rs = psS.executeQuery();
                if(rs.next()){
                    throw new DaoException("Receipt no already exists");
                }
            }
            finally{
                closeStatement(psS);
            }
            PreparedStatement psI = con.prepareStatement(INSERT_FEERECEIPT);
            try{
                psI.setString(1, feeReceipt.getReceiptNo());
                psI.setDate(2, feeReceipt.getReceiptDate());
                psI.setInt(3, feeReceipt.getAdmissionId());
                psI.setInt(4, feeReceipt.getAmount());
                psI.setString(5, feeReceipt.getDetail());
                psI.setString(6, feeReceipt.getFeeType());
                psI.executeUpdate();
            }
            finally{
                closeStatement(psI);
            }
            con.commit();
        }
        catch(SQLException | DaoException e){
            con.rollback();
            throw e;
        }
        finally{
            con.setAutoCommit(false);
            closeConnection(con);
        }
    }
}
