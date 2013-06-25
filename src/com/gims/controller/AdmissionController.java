package com.gims.controller;

import com.gims.model.dao.AdmissionDao;
import com.gims.model.dao.DaoException;
import com.gims.model.dto.Admission;
import com.gims.model.dto.Course;
import com.gims.model.dto.University;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * @author sukumar sen
 */
public class AdmissionController {

    public AdmissionController() {
    }
    public boolean loadUniversities(List<University> universityList){
        return new UniversityController().loadUniversities(universityList);
    }
    public boolean loadCategories(List<String> categoryList){
        return new CategoryController().loadCategories(categoryList);
    }
    public boolean loadCourses(String university, String category, List<Course> courseList){
        return new CourseController().loadCourses(university, category, courseList);
    }
    public boolean loadAdmissions(int courseId, List<Admission> admissionList){
        try{
            new AdmissionDao().readAll(courseId, admissionList);
            Collections.sort(admissionList);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean loadAdmissions( List<Admission> admissionList){
        try{
            new AdmissionDao().readAll(admissionList);
            Collections.sort(admissionList);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean loadAdmissionsActiveWithoutDetail( int courseId, List<Admission> admissionList){
        try{
            new AdmissionDao().readAllActiveWithoutDetail(courseId, admissionList);
            Collections.sort(admissionList);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean loadAdmissionsActiveWithoutDetail( List<Admission> admissionList){
        try{
            new AdmissionDao().readAllActiveWithoutDetail(admissionList);
            Collections.sort(admissionList);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean loadAdmissionsActiveWithDetailWithFee( int courseId, List<Admission> admissionList){
        try{
            new AdmissionDao().readAllActiveWithDetailWithFee(courseId, admissionList);
            Collections.sort(admissionList);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean loadAdmissionsActiveWithDetailWithFee(List<Admission> admissionList){
        try{
            new AdmissionDao().readAllActiveWithDetailWithFee(admissionList);
            Collections.sort(admissionList);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean saveAdmission(Admission admission, List<String> msgs){
        try{
            new AdmissionDao().insert(admission);
        }
        catch(DaoException e){
            System.out.println(e.getMessage());
            msgs.add("S.No. already exists");
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public boolean updateAdmission(Admission admission, List<String> msgs){
        try{
            new AdmissionDao().update(admission);
        }
        catch(DaoException e){
            System.out.println(e.getMessage());
            msgs.add("S.No. already exists");
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
