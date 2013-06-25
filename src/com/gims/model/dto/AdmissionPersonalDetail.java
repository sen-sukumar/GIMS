package com.gims.model.dto;
import java.sql.Date;
/**
 * @author sukumar sen
 */
public class AdmissionPersonalDetail {
    private String gender;
    private Date dob;
    private Short age;
    private String fatherName;
    private String motherName;
    private String phone;
    private String email;
    private String presentAddress;
    private String permanentAddress;
    private byte[] photo;

    public AdmissionPersonalDetail() {
    }

    public AdmissionPersonalDetail(String gender, Date dob, short age, String fatherName, String motherName, String phone, String presentAddress, String permanentAddress, byte[] photo) {
        this.gender = gender;
        this.dob = dob;
        this.age = age;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.phone = phone;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
