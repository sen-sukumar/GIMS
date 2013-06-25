package com.gims.model.dto;
/**
 * @author sukumar sen
 */
public class Course implements Comparable<Course> {
    private String name;
    private int durationYrs;
    private int fee;
    private int examFee;
    private int courseId;

    public Course(String name, int durationYrs, int fee, int examFee) {
        this(name, durationYrs, fee, examFee, -1);
    }
    public Course(String name, int durationYrs, int fee, int examFee, int courseId) {
        this.name = name;
        this.durationYrs = durationYrs;
        this.fee = fee;
        this.examFee = examFee;
        this.courseId = courseId;
    }
    public Course() { }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationYrs() {
        return durationYrs;
    }

    public void setDurationYrs(int durationYrs) {
        this.durationYrs = durationYrs;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    @Override
    public int compareTo(Course o) {
        return this.getName().compareTo(o.getName());
    }

    public int getExamFee() {
        return examFee;
    }

    public void setExamFee(int examFee) {
        this.examFee = examFee;
    }
    public int getCourseId() {
        return courseId;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
