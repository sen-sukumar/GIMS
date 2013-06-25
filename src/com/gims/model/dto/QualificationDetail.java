package com.gims.model.dto;
import java.sql.Date;
/**
 * @author sukumar sen
 */
public class QualificationDetail {
    private String examName;
    private Date passDate;
    private String schoolCollege;
    private String boardUniversity;
    private String certificateNo;
    private String RegRollNo;
    private Short percentage;

    public QualificationDetail(String examName, Date passDate, String schoolCollege, String boardUniversity, String certificateNo, String RegRollNo, Short percentage) {
        this.examName = examName;
        this.passDate = passDate;
        this.schoolCollege = schoolCollege;
        this.boardUniversity = boardUniversity;
        this.certificateNo = certificateNo;
        this.RegRollNo = RegRollNo;
        this.percentage = percentage;
    }

    public QualificationDetail() {
    }
    
    

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Date getPassDate() {
        return passDate;
    }

    public void setPassDate(Date passDate) {
        this.passDate = passDate;
    }

    public String getBoardUniversity() {
        return boardUniversity;
    }

    public void setBoardUniversity(String boardUniversity) {
        this.boardUniversity = boardUniversity;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getRegRollNo() {
        return RegRollNo;
    }

    public void setRegRollNo(String RegRollNo) {
        this.RegRollNo = RegRollNo;
    }

    public Short getPercentage() {
        return percentage;
    }

    public void setPercentage(Short percentage) {
        this.percentage = percentage;
    }  

    public String getSchoolCollege() {
        return schoolCollege;
    }

    public void setSchoolCollege(String schoolCollege) {
        this.schoolCollege = schoolCollege;
    }
    
}
