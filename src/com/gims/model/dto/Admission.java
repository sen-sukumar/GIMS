package com.gims.model.dto;
import java.sql.Date;
import java.util.List;
/**
 * @author sukumar sen
 */
public class Admission implements Comparable<Admission>{
    private int id;
    private String sno;
    private String name;
    private Date date;
    private int courseId;
    private String university;
    private String category;
    private int fee;
    private int examFee;
    private short academicYrFrom;
    private short academicYrTo;
    private boolean complete;
    private AdmissionPersonalDetail detail;
    private List<QualificationDetail> qualificationList;
    private List<FeeReceipt> feeReceiptList;
    public Admission() {
    }
    public Admission(String sno, String name, Date date, int courseId, int fee, int examFee, short academicYrFrom, short academicYrTo) {
        this.sno = sno;
        this.name = name;
        this.date = date;
        this.courseId = courseId;
        this.fee = fee;
        this.examFee = examFee;
        this.academicYrFrom = academicYrFrom;
        this.academicYrTo = academicYrTo;
        this.complete = false;
    }
    
    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getExamFee() {
        return examFee;
    }

    public void setExamFee(int examFee) {
        this.examFee = examFee;
    }

    public short getAcademicYrFrom() {
        return academicYrFrom;
    }

    public void setAcademicYrFrom(short academicYrFrom) {
        this.academicYrFrom = academicYrFrom;
    }

    public short getAcademicYrTo() {
        return academicYrTo;
    }

    public void setAcademicYrTo(short academicYrTo) {
        this.academicYrTo = academicYrTo;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    public void setAdmissionPersonalDetail(AdmissionPersonalDetail detail){
        this.detail = detail;
    }
    public AdmissionPersonalDetail getAdmissionPersonalDetail(){
        return detail;
    }

    public List<QualificationDetail> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<QualificationDetail> qualificationList) {
        this.qualificationList = qualificationList;
    }
    public List<FeeReceipt> getFeeReceiptList() {
        return feeReceiptList;
    }

    public void setFeeReceiptList(List<FeeReceipt> feeReceiptList) {
        this.feeReceiptList = feeReceiptList;
    }
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Admission o) {
        return this.getName().compareTo(o.getName());
    }
}
