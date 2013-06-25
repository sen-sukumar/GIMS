package com.gims.model.dao;

import com.gims.model.dto.Admission;
import com.gims.model.dto.AdmissionPersonalDetail;
import com.gims.model.dto.FeeReceipt;
import com.gims.model.dto.QualificationDetail;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
/**
 * @author sukumar sen
 */
public class AdmissionDao extends BasicDao{
    public static final String INSERT_ADMISSION_SQL = "insert into admission (sln, name, admission_date, course_id, fee, exam_fee, academic_yr_from, academic_yr_to, course_complete)"+
                                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_ADMISSION_DETAIL_SQL = "insert into admission_student_detail (admission_id, gender, date_of_birth, age, father_name, mother_name, phone, email, present_address, permanent_address, photo)"+
                                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_QUALIFICATION_SQL = "insert into admission_qualification(admission_id,exam_name,pass_date,school_college_name,board_university_name,certificate_no,reg_roll_no,percentage)"+
                                                          "values(?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ADMISSION_WITH_DETAIL = "select admission.id , sln, admission.name, admission_date, "
            + "admission.fee, admission.exam_fee, academic_yr_from, academic_yr_to, course_id, course_complete, gender, date_of_birth, age, father_name,"
            + " mother_name, admission_student_detail.phone, present_address, permanent_address, email, photo, university.name as university_name,"
            + " category.name as category_name from admission, admission_student_detail, course, university, category "
            + "where admission.id = admission_id and course_id = course.id and course.university_id = university.id  and course.category_id = category.id and course_id = ?";
    public static final String SELECT_ALL_ADMISSION_WITH_DETAIL = "select admission.id , sln, admission.name, admission_date, "
            + "admission.fee, admission.exam_fee, academic_yr_from, academic_yr_to, course_id, course_complete, gender, date_of_birth, age, father_name,"
            + " mother_name, admission_student_detail.phone, present_address, permanent_address, email, photo, university.name as university_name,"
            + " category.name as category_name from admission, admission_student_detail, course, university, category "
            + "where admission.id = admission_id and course_id = course.id and course.university_id = university.id  and course.category_id = category.id";
    public static final String SELECT_ADMISSION_ACTIVE_WITH_DETAIL = "select admission.id , sln, admission.name, admission_date, "
            + "admission.fee, admission.exam_fee, academic_yr_from, academic_yr_to, course_id, gender, date_of_birth, age, father_name,"
            + " mother_name, admission_student_detail.phone, present_address, permanent_address, email, photo, university.name as university_name,"
            + " category.name as category_name from admission, admission_student_detail, course, university, category "
            + "where admission.id = admission_id and course_id = course.id and course.university_id = university.id  and course.category_id = category.id and course_id = ? and course_complete='N'";
    public static final String SELECT_ALL_ADMISSION_ACTIVE_WITH_DETAIL = "select admission.id , sln, admission.name, admission_date, "
            + "admission.fee, admission.exam_fee, academic_yr_from, academic_yr_to, course_id, gender, date_of_birth, age, father_name,"
            + " mother_name, admission_student_detail.phone, present_address, permanent_address, email, photo, university.name as university_name,"
            + " category.name as category_name from admission, admission_student_detail, course, university, category "
            + "where admission.id = admission_id and course_id = course.id and course.university_id = university.id  and course.category_id = category.id and course_complete='N'";
    public static final String SELECT_ALL_ADMISSION_ACTIVE_WITHOUT_DETAIL = "select admission.id , sln, admission.name, course_id, admission.fee, admission.exam_fee, "
            + " university.name as university_name, category.name as category_name from admission, course, university, category "
            + " where course_id = course.id and course.university_id = university.id  and course.category_id = category.id and course_complete='N'";
    public static final String SELECT_ADMISSION_ACTIVE_WITHOUT_DETAIL = "select admission.id , sln, admission.name, course_id, admission.fee, admission.exam_fee, "
            + " university.name as university_name, category.name as category_name from admission, course, university, category "
            + " where course_id = course.id and course.university_id = university.id  and course.category_id = category.id and course_complete='N' and course_id=?";
    public static final String SELECT_FEE_RECEIPT = "select receipt_no, receipt_date, amount, detail, id, fee_type from fee_receipt "
            + "where admission_id = ?";
    public static final String SELECT_QUALIFICATION_SQL = "select * from admission_qualification where admission_id = ?";
    public static final String UPDATE_ADMISSION_SQL = "update admission set sln=?, name=?, admission_date=?,course_id=?, fee=?,"
            + " exam_fee=?, academic_yr_from=?, academic_yr_to=?, course_complete=? where id=?";
    public static final String UPDATE_ADMISSION_DETAIL_SQL = "update admission_student_detail set gender=?,date_of_birth=?, age=?,"
            + " father_name=?, mother_name=?, phone=?, present_address=?, permanent_address=?,photo=?, email=? where admission_id=?";
    public static final String DELETE_QUALIFICATION_DETAIL_SQL = "delete from admission_qualification where admission_id=?";
    public void insert(Admission admission) throws SQLException, DaoException{
        Connection con = getConnection();
        try{
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            Statement st = con.createStatement();
            try{
                ResultSet rs = st.executeQuery("select * from admission where sln='" + admission.getSno()+ "'");
                if(rs.next()){
                    throw new DaoException("sln alreday exists");
                }
            }
            catch(DaoException e){
                con.rollback();
                throw e;
            }
            finally{
                closeStatement(st);
            }
            
            PreparedStatement ps = con.prepareStatement(INSERT_ADMISSION_SQL);
            int admissionId = -1;
            try{
                ps.setString(1, admission.getSno());
                ps.setString(2, admission.getName());
                Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                calendar.setTimeInMillis(0);
                ps.setDate(3, admission.getDate(), calendar);
                ps.setInt(4, admission.getCourseId());
                ps.setInt(5, admission.getFee());
                ps.setInt(6, admission.getExamFee());
                ps.setShort(7, admission.getAcademicYrFrom());
                ps.setShort(8, admission.getAcademicYrTo());
                String ch = admission.isComplete() ? "Y" : "N";
                ps.setString(9, ch);
                int n = ps.executeUpdate();
                if(n != 1){
                    throw new SQLException("Insert failed");
                }
                ResultSet rs = con.createStatement().executeQuery("select admission_seq.currval from dual");
                if(rs.next()){
                    admissionId = rs.getInt(1);
                }
                else{
                    throw new SQLException("problem in retrieving generated key");
                }
                
            }
            finally{
                closeStatement(ps);
            }
            
            ps = con.prepareStatement(INSERT_ADMISSION_DETAIL_SQL);
            try{
                AdmissionPersonalDetail detail = admission.getAdmissionPersonalDetail();
                ps.setInt(1, admissionId);
                ps.setString(2, detail.getGender());
                if(detail.getDob() != null){
                    ps.setDate(3, detail.getDob(), Calendar.getInstance(TimeZone.getTimeZone("UTC")));
                }
                else{
                    ps.setNull(3, Types.DATE);
                }
                if(detail.getAge() != null){
                    ps.setShort(4, detail.getAge());
                }
                else{
                    ps.setNull(4, Types.SMALLINT);
                }
                if(detail.getFatherName() != null){
                    ps.setString(5, detail.getFatherName());
                }
                else{
                    ps.setNull(5, Types.VARCHAR);
                }
                if(detail.getMotherName() != null){
                    ps.setString(6, detail.getMotherName());
                }
                else{
                    ps.setNull(6, Types.VARCHAR);
                }
                if(detail.getPhone() != null){
                    ps.setString(7, detail.getPhone());
                }
                else{
                    ps.setNull(7, Types.VARCHAR);
                }
                if(detail.getEmail()!= null){
                    ps.setString(8, detail.getEmail());
                }
                else{
                    ps.setNull(8, Types.VARCHAR);
                }
                if(detail.getPresentAddress() != null){
                    ps.setString(9, detail.getPresentAddress());
                }
                else{
                    ps.setNull(9, Types.VARCHAR);
                }
                if(detail.getPermanentAddress() != null){
                    ps.setString(10, detail.getPermanentAddress());
                }
                else{
                    ps.setNull(10, Types.VARCHAR);
                }
                if(detail.getPhoto() != null){
                    ps.setBlob(11, new ByteArrayInputStream(detail.getPhoto()), detail.getPhoto().length);
                }
                else{
                    ps.setNull(11, Types.BLOB);
                }
                int n = ps.executeUpdate();
                if(n != 1){
                    throw new SQLException("Insert failed");
                }
            }
            finally{
                closeStatement(ps);
            }
            ps = con.prepareStatement(INSERT_QUALIFICATION_SQL);
            try{
                List<QualificationDetail> qList = admission.getQualificationList();
                if(qList != null){
                    for(QualificationDetail qDetail : qList){
                        ps.setInt(1,admissionId);
                        ps.setString(2, qDetail.getExamName());
                        if(qDetail.getPassDate() != null)
                            ps.setDate(3, qDetail.getPassDate());
                        else
                            ps.setNull(3, Types.DATE);
                        if(qDetail.getSchoolCollege() != null)
                            ps.setString(4, qDetail.getSchoolCollege());
                        else
                            ps.setNull(4, Types.VARCHAR);
                        if(qDetail.getBoardUniversity() != null)
                            ps.setString(5, qDetail.getBoardUniversity());
                        else
                            ps.setNull(5, Types.VARCHAR);
                        if(qDetail.getCertificateNo()!= null)
                            ps.setString(6, qDetail.getCertificateNo());
                        else
                            ps.setNull(6, Types.VARCHAR);
                        if(qDetail.getRegRollNo()!= null)
                            ps.setString(7, qDetail.getRegRollNo());
                        else
                            ps.setNull(7, Types.VARCHAR);
                        if(qDetail.getPercentage()!= null)
                            ps.setShort(8, qDetail.getPercentage());
                        else
                            ps.setNull(8, Types.SMALLINT);
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
            }
            finally{
                closeStatement(ps);
            }
            con.commit();
        }
        catch(SQLException e){
            con.rollback();
            throw e;
        }
        finally{
             con.setAutoCommit(true);
             closeConnection(con);
        }
        
    }
    public void readAll(int courseId, List<Admission> admissionList) throws SQLException{
        Connection con = getConnection();
        try{
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(SELECT_ADMISSION_WITH_DETAIL);
            try{
                ps.setInt(1, courseId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Admission admission = new Admission();
                    admission.setId(rs.getInt("id"));
                    admission.setCourseId(rs.getInt("course_id"));
                    admission.setName(rs.getString("name"));
                    admission.setSno(rs.getString("sln"));
                    admission.setDate(rs.getDate("admission_date"));
                    admission.setFee(rs.getInt("fee"));
                    admission.setExamFee(rs.getInt("exam_fee"));
                    admission.setAcademicYrFrom(rs.getShort("academic_yr_from"));
                    admission.setAcademicYrTo(rs.getShort("academic_yr_to"));
                    admission.setComplete("Y".equals(rs.getString("course_complete"))? true : false);
                    admission.setUniversity(rs.getString("university_name"));
                    admission.setCategory(rs.getString("category_name"));
                    
                    AdmissionPersonalDetail detail = new AdmissionPersonalDetail();
                    detail.setGender(rs.getString("gender"));
                    detail.setDob(rs.getDate("date_of_birth"));
                    detail.setAge(rs.getShort("age"));
                    detail.setFatherName(rs.getString("father_name"));
                    detail.setMotherName(rs.getString("mother_name"));
                    detail.setPhone(rs.getString("phone"));
                    detail.setEmail(rs.getString("email"));
                    detail.setPresentAddress(rs.getString("present_address"));
                    detail.setPermanentAddress(rs.getString("permanent_address"));
                    Blob blob = rs.getBlob("photo");
                    if(blob == null){
                        detail.setPhoto(null);
                    }
                    else{
                        InputStream in = blob.getBinaryStream();
                        try {
                            ByteArrayOutputStream bios = new ByteArrayOutputStream();
                            byte[] buf = new byte[1024];
                            int n = 0;
                            while ((n = in.read(buf)) >= 0) {
                                bios.write(buf, 0, n);
                            }
                            detail.setPhoto(bios.toByteArray());
                        }
                        catch(IOException ex){
                            detail.setPhoto(null);
                        }
                        finally{
                            try {
                                in.close();
                            } catch (IOException ex) {}
                        }
                    }
                    admission.setAdmissionPersonalDetail(detail);
                    
                    //qualifications
                    PreparedStatement psQ = con.prepareStatement(SELECT_QUALIFICATION_SQL);
                    try{
                        List<QualificationDetail> qList = new ArrayList<>();
                        psQ.setInt(1, admission.getId());
                        ResultSet rsQ = psQ.executeQuery();
                        while(rsQ.next()){
                            QualificationDetail qualification = new QualificationDetail();
                            qualification.setExamName(rsQ.getString("exam_name"));
                            qualification.setSchoolCollege(rsQ.getString("school_college_name"));
                            qualification.setBoardUniversity(rsQ.getString("board_university_name"));
                            qualification.setCertificateNo(rsQ.getString("certificate_no"));
                            qualification.setRegRollNo(rsQ.getString("reg_roll_no"));
                            qualification.setPercentage(rsQ.getShort("percentage"));
                            qualification.setPassDate(rsQ.getDate("pass_date"));
                            qList.add(qualification);
                        }
                        if(qList.size() > 0)
                            admission.setQualificationList(qList);
                        else
                            admission.setQualificationList(null);
                    }
                    finally{
                        closeStatement(psQ);
                    }
                    admissionList.add(admission);
                }
                
            }
            finally{
                closeStatement(ps);
            }
            con.commit();
        }
        catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally{
            con.setAutoCommit(true);
            closeConnection(con);
        }
    }
    
    public void readAll(List<Admission> admissionList) throws SQLException{
        Connection con = getConnection();
        try{
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_ADMISSION_WITH_DETAIL);
            try{
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Admission admission = new Admission();
                    admission.setId(rs.getInt("id"));
                    admission.setCourseId(rs.getInt("course_id"));
                    admission.setName(rs.getString("name"));
                    admission.setSno(rs.getString("sln"));
                    admission.setDate(rs.getDate("admission_date"));
                    admission.setFee(rs.getInt("fee"));
                    admission.setExamFee(rs.getInt("exam_fee"));
                    admission.setAcademicYrFrom(rs.getShort("academic_yr_from"));
                    admission.setAcademicYrTo(rs.getShort("academic_yr_to"));
                    admission.setComplete("Y".equals(rs.getString("course_complete"))? true : false);
                    admission.setUniversity(rs.getString("university_name"));
                    admission.setCategory(rs.getString("category_name"));
                    
                    AdmissionPersonalDetail detail = new AdmissionPersonalDetail();
                    detail.setGender(rs.getString("gender"));
                    detail.setDob(rs.getDate("date_of_birth"));
                    detail.setAge(rs.getShort("age"));
                    detail.setFatherName(rs.getString("father_name"));
                    detail.setMotherName(rs.getString("mother_name"));
                    detail.setPhone(rs.getString("phone"));
                    detail.setEmail(rs.getString("email"));
                    detail.setPresentAddress(rs.getString("present_address"));
                    detail.setPermanentAddress(rs.getString("permanent_address"));
                    Blob blob = rs.getBlob("photo");
                    if(blob == null){
                        detail.setPhoto(null);
                    }
                    else{
                        InputStream in = blob.getBinaryStream();
                        ByteArrayOutputStream bios = new ByteArrayOutputStream();
                        try {
                            byte[] buf = new byte[1024];
                            int n = 0;
                            while ((n = in.read(buf)) >= 0) {
                                bios.write(buf, 0, n);
                            }
                            detail.setPhoto(bios.toByteArray());
                        }
                        catch(IOException ex){
                            detail.setPhoto(null);
                        }
                        finally{
                            try {
                                in.close();
                            } catch (IOException ex) {}
                            try{
                                bios.close();
                            }
                            catch(IOException e){}
                        }
                    }
                    admission.setAdmissionPersonalDetail(detail);
                    
                    //qualifications
                    PreparedStatement psQ = con.prepareStatement(SELECT_QUALIFICATION_SQL);
                    try{
                        List<QualificationDetail> qList = new ArrayList<>();
                        psQ.setInt(1, admission.getId());
                        ResultSet rsQ = psQ.executeQuery();
                        while(rsQ.next()){
                            QualificationDetail qualification = new QualificationDetail();
                            qualification.setExamName(rsQ.getString("exam_name"));
                            qualification.setSchoolCollege(rsQ.getString("school_college_name"));
                            qualification.setBoardUniversity(rsQ.getString("board_university_name"));
                            qualification.setCertificateNo(rsQ.getString("certificate_no"));
                            qualification.setRegRollNo(rsQ.getString("reg_roll_no"));
                            qualification.setPercentage(rsQ.getShort("percentage"));
                            qualification.setPassDate(rsQ.getDate("pass_date"));
                            qList.add(qualification);
                        }
                        if(qList.size() > 0)
                            admission.setQualificationList(qList);
                        else
                            admission.setQualificationList(null);
                    }
                    finally{
                        closeStatement(psQ);
                    }
                    admissionList.add(admission);
                }
                
            }
            finally{
                closeStatement(ps);
            }
            con.commit();
        }
        catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally{
            con.setAutoCommit(true);
            closeConnection(con);
        }
    }
    public void readAllActiveWithoutDetail(List<Admission> admissionList)throws SQLException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_ADMISSION_ACTIVE_WITHOUT_DETAIL);
            try{
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Admission admission = new Admission();
                    admission.setId(rs.getInt("id"));
                    admission.setSno(rs.getString("sln"));
                    admission.setName(rs.getString("name"));
                    admission.setCourseId(rs.getInt("course_id"));
                    admission.setFee(rs.getInt("fee"));
                    admission.setExamFee(rs.getInt("exam_fee"));
                    admission.setUniversity(rs.getString("university_name"));
                    admission.setCategory(rs.getString("category_name"));
                    admissionList.add(admission);
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
    public void readAllActiveWithoutDetail(int courseId, List<Admission> admissionList)throws SQLException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_ADMISSION_ACTIVE_WITHOUT_DETAIL);
            try{
                ps.setInt(1, courseId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Admission admission = new Admission();
                    admission.setId(rs.getInt("id"));
                    admission.setSno(rs.getString("sln"));
                    admission.setName(rs.getString("name"));
                    admission.setCourseId(rs.getInt("course_id"));
                    admission.setFee(rs.getInt("fee"));
                    admission.setExamFee(rs.getInt("exam_fee"));
                    admission.setUniversity(rs.getString("university_name"));
                    admission.setCategory(rs.getString("category_name"));
                    admissionList.add(admission);
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
    public void readAllActiveWithDetailWithFee(List<Admission> admissionList)throws SQLException{
        Connection con = getConnection();
        try{
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement ps = con.prepareStatement(SELECT_ALL_ADMISSION_ACTIVE_WITH_DETAIL);
            try{
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Admission admission = new Admission();
                    admission.setId(rs.getInt("id"));
                    admission.setSno(rs.getString("sln"));
                    admission.setName(rs.getString("name"));
                    admission.setCourseId(rs.getInt("course_id"));
                    admission.setFee(rs.getInt("fee"));
                    admission.setExamFee(rs.getInt("exam_fee"));
                    admission.setUniversity(rs.getString("university_name"));
                    admission.setCategory(rs.getString("category_name"));
                    AdmissionPersonalDetail detail = new AdmissionPersonalDetail();
                    detail.setAge(rs.getShort("age") == 0 ? null : rs.getShort("age"));
                    detail.setEmail(rs.getString("email"));
                    detail.setPhone(rs.getString("phone"));
                    Blob blob = rs.getBlob("photo");
                    if(rs.wasNull()){
                        detail.setPhoto(null);
                    }
                    else{
                        InputStream in = blob.getBinaryStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        try{
                            byte[] buff = new byte[1024];
                            int n = 0;
                            while( (n = in.read(buff)) >= 0){
                                bos.write(buff, 0, n);
                            }
                            detail.setPhoto(bos.toByteArray());
                        }
                        catch(IOException ie){
                            detail.setPhoto(null);
                        }
                        finally{
                            try{
                                in.close();
                            }
                            catch(IOException ie){}
                            try{
                                bos.close();
                            }
                            catch(IOException ie){}
                        }
                    }
                    admission.setAdmissionPersonalDetail(detail);
                    PreparedStatement psF = con.prepareStatement(SELECT_FEE_RECEIPT);
                    try{
                        psF.setInt(1, admission.getId());
                        ResultSet rsF = psF.executeQuery();
                        List<FeeReceipt> feeReceiptList = new ArrayList<>();
                        while(rsF.next()){
                            FeeReceipt feeReceipt = new FeeReceipt();
                            feeReceipt.setId(rsF.getLong("id"));
                            feeReceipt.setReceiptNo(rsF.getString("receipt_no"));
                            feeReceipt.setAmount(rsF.getInt("amount"));
                            feeReceipt.setDetail(rsF.getString("detail"));
                            feeReceipt.setFeeType(rsF.getString("fee_type"));
                            feeReceipt.setReceiptDate(rsF.getDate("receipt_date"));
                            feeReceiptList.add(feeReceipt);
                        }
                        admission.setFeeReceiptList(feeReceiptList);
                    }
                    finally{
                        closeStatement(psF);
                    }
                    
                    admissionList.add(admission);
                }
            }
            finally{
                closeStatement(ps);
            }
            con.commit();
        }
        catch(SQLException e){
            con.rollback();
            throw e;
        }
        finally{
            con.setAutoCommit(false);
            closeConnection(con);
        }
    }
    public void readAllActiveWithDetailWithFee(int courseId, List<Admission> admissionList)throws SQLException{
        Connection con = getConnection();
        try{
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            PreparedStatement ps = con.prepareStatement(SELECT_ADMISSION_ACTIVE_WITH_DETAIL);
            try{
                ps.setInt(1, courseId);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    Admission admission = new Admission();
                    admission.setId(rs.getInt("id"));
                    admission.setSno(rs.getString("sln"));
                    admission.setName(rs.getString("name"));
                    admission.setCourseId(rs.getInt("course_id"));
                    admission.setFee(rs.getInt("fee"));
                    admission.setExamFee(rs.getInt("exam_fee"));
                    admission.setUniversity(rs.getString("university_name"));
                    admission.setCategory(rs.getString("category_name"));
                    AdmissionPersonalDetail detail = new AdmissionPersonalDetail();
                    detail.setAge(rs.getShort("age") == 0 ? null : rs.getShort("age"));
                    detail.setEmail(rs.getString("email"));
                    detail.setPhone(rs.getString("phone"));
                    Blob blob = rs.getBlob("photo");
                    if(rs.wasNull()){
                        detail.setPhoto(null);
                    }
                    else{
                        InputStream in = blob.getBinaryStream();
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        try{
                            byte[] buff = new byte[1024];
                            int n = 0;
                            while( (n = in.read(buff)) >= 0){
                                bos.write(buff, 0, n);
                            }
                            detail.setPhoto(bos.toByteArray());
                        }
                        catch(IOException ie){
                            detail.setPhoto(null);
                        }
                        finally{
                            try{
                                in.close();
                            }
                            catch(IOException ie){}
                            try{
                                bos.close();
                            }
                            catch(IOException ie){}
                        }
                    }
                    admission.setAdmissionPersonalDetail(detail);
                    PreparedStatement psF = con.prepareStatement(SELECT_FEE_RECEIPT);
                    try{
                        psF.setInt(1, admission.getId());
                        ResultSet rsF = psF.executeQuery();
                        List<FeeReceipt> feeReceiptList = new ArrayList<>();
                        while(rsF.next()){
                            FeeReceipt feeReceipt = new FeeReceipt();
                            feeReceipt.setId(rs.getLong("id"));
                            feeReceipt.setReceiptNo(rs.getString("receipt_no"));
                            feeReceipt.setAmount(rs.getInt("amount"));
                            feeReceipt.setDetail(rs.getString("detail"));
                            feeReceipt.setFeeType(rs.getString("fee_type"));
                            feeReceipt.setReceiptDate(rs.getDate("receipt_date"));
                            feeReceiptList.add(feeReceipt);
                        }
                        admission.setFeeReceiptList(feeReceiptList);
                    }
                    finally{
                        closeStatement(psF);
                    }
                    
                    admissionList.add(admission);
                }
            }
            finally{
                closeStatement(ps);
            }
            con.commit();
        }
        catch(SQLException e){
            con.rollback();
            throw e;
        }
        finally{
            con.setAutoCommit(false);
            closeConnection(con);
        }
    }
    public void update(Admission admission)throws SQLException, DaoException{
        Connection con = getConnection();
        try{
            con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            con.setAutoCommit(false);
            
            Statement st = con.createStatement();
            try{
                ResultSet rs = st.executeQuery("select * from admission where sln='" + admission.getSno()+ "' and id<>" + admission.getId());
                if(rs.next()){
                    throw new DaoException("sln alreday exists");
                }
            }
            catch(DaoException e){
                con.rollback();
                throw e;
            }
            finally{
                closeStatement(st);
            }
            
            PreparedStatement ps = con.prepareStatement(UPDATE_ADMISSION_SQL);
            try{
                ps.setString(1, admission.getSno());
                ps.setString(2, admission.getName());
                ps.setDate(3, admission.getDate());
                ps.setInt(4, admission.getCourseId());
                ps.setInt(5, admission.getFee());
                ps.setInt(6, admission.getExamFee());
                ps.setShort(7, admission.getAcademicYrFrom());
                ps.setShort(8, admission.getAcademicYrTo());
                ps.setString(9, admission.isComplete() ? "Y" : "N");
                ps.setInt(10, admission.getId());
                
                int n = ps.executeUpdate();
                if(n != 1){
                    throw new SQLException("Update failed");
                }
            }
            finally{
                closeStatement(ps);
            }
            ps = con.prepareStatement(UPDATE_ADMISSION_DETAIL_SQL);
            try{
                AdmissionPersonalDetail detail = admission.getAdmissionPersonalDetail();
                ps.setString(1, detail.getGender());
                if(detail.getDob() != null)
                    ps.setDate(2, detail.getDob());
                else
                    ps.setNull(2, Types.DATE);
                if(detail.getAge() != null)
                    ps.setShort(3, detail.getAge());
                else
                    ps.setNull(3, Types.SMALLINT);
                if(detail.getFatherName() != null)
                    ps.setString(4, detail.getFatherName());
                else
                    ps.setNull(4, Types.VARCHAR);
                if(detail.getMotherName()!= null)
                    ps.setString(5, detail.getMotherName());
                else
                    ps.setNull(5, Types.VARCHAR);
                if(detail.getPhone() != null)
                    ps.setString(6, detail.getPhone());
                else
                    ps.setNull(6, Types.VARCHAR);
                if(detail.getPresentAddress()!= null)
                    ps.setString(7, detail.getPresentAddress());
                else
                    ps.setNull(7, Types.VARCHAR);
                if(detail.getPermanentAddress()!= null)
                    ps.setString(8, detail.getPermanentAddress());
                else
                    ps.setNull(8, Types.VARCHAR);
                if(detail.getPhoto() != null)
                    ps.setBlob(9, new ByteArrayInputStream(detail.getPhoto()), detail.getPhoto().length);
                else
                    ps.setNull(9, Types.BLOB);
                if(detail.getEmail()!= null){
                    ps.setString(10, detail.getEmail());
                }
                else{
                    ps.setNull(10, Types.VARCHAR);
                }
                
                ps.setInt(11, admission.getId());
                int n = ps.executeUpdate();
                if(n != 1){
                    throw new SQLException("Update failed");
                }
            }
            finally{
                closeStatement(ps);
            }
            //qualifications
            ps = con.prepareStatement(DELETE_QUALIFICATION_DETAIL_SQL);
            try{
                ps.setInt(1, admission.getId());
                ps.executeUpdate();
                List<QualificationDetail> qList = admission.getQualificationList();
                if(qList != null){
                    PreparedStatement psq = con.prepareStatement(INSERT_QUALIFICATION_SQL);
                    try{
                       for(QualificationDetail detail : qList){
                           psq.setInt(1, admission.getId());
                           if(detail.getExamName() != null)
                                psq.setString(2, detail.getExamName());
                           else
                               psq.setNull(2, Types.VARCHAR);
                           if(detail.getPassDate()!= null)
                                psq.setDate(3, detail.getPassDate());
                           else
                               psq.setNull(3, Types.DATE);
                           if(detail.getSchoolCollege()!= null)
                                psq.setString(4, detail.getSchoolCollege());
                           else
                               psq.setNull(4, Types.VARCHAR);
                           if(detail.getBoardUniversity()!= null)
                                psq.setString(5, detail.getBoardUniversity());
                           else
                               psq.setNull(5, Types.VARCHAR);
                           if(detail.getCertificateNo()!= null)
                                psq.setString(6, detail.getCertificateNo());
                           else
                               psq.setNull(6, Types.VARCHAR);
                           if(detail.getRegRollNo()!= null)
                                psq.setString(7, detail.getRegRollNo());
                           else
                               psq.setNull(7, Types.VARCHAR);
                           if(detail.getPercentage()!= null)
                                psq.setShort(8, detail.getPercentage());
                           else
                               psq.setNull(4, Types.SMALLINT);
                           psq.addBatch();
                        }
                        psq.executeBatch();
                    }
                    finally{
                        closeStatement(psq);
                    }
                    
                }
            }
            finally{
                closeStatement(ps);
            }
            
            con.commit();
        }
        catch(SQLException ex){
            con.rollback();
            throw ex;
        }
        finally{
            con.setAutoCommit(false);
            closeConnection(con);
        }
    }
}
