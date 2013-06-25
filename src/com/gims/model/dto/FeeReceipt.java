package com.gims.model.dto;

import java.sql.Date;

/**
 * @author sukumar sen
 */
public class FeeReceipt implements Comparable<FeeReceipt>{
    private String receiptNo;
    private Date receiptDate;
    private int admissionId;
    private int amount;
    private String detail;
    private String feeType;
    private long id;
   public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    
    public long getId(){
        return id;
    }
    public void setId(long id){
        this.id = id;
    }
    
     @Override
    public int compareTo(FeeReceipt o) {
        return this.getReceiptDate().compareTo(o.getReceiptDate());
    }
}
