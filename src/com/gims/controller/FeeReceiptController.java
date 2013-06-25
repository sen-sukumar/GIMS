package com.gims.controller;
import com.gims.model.dao.DaoException;
import com.gims.model.dao.FeeReceiptDao;
import com.gims.model.dto.Admission;
import com.gims.model.dto.Course;
import com.gims.model.dto.FeeReceipt;
import com.gims.model.dto.University;
import java.sql.SQLException;
import java.util.List;
/**
 * @author sukumar sen
 */
public class FeeReceiptController {
    public boolean loadUniversities(List<University> universityList){
        return new UniversityController().loadUniversities(universityList);
    }
    public boolean loadCategories(List<String> categoryList){
        return new CategoryController().loadCategories(categoryList);
    }
    public boolean loadCourses(String university, String category, List<Course> courseList){
        return new CourseController().loadCourses(university, category, courseList);
    }
    public boolean loadAdmissions(List<Admission> admissionList){
        return new AdmissionController().loadAdmissionsActiveWithoutDetail(admissionList);
    }
    public boolean loadAdmissions(int courseId, List<Admission> admissionList){
        return new AdmissionController().loadAdmissionsActiveWithoutDetail(courseId, admissionList);
    }
    public boolean saveFeeReceipt(FeeReceipt feeReceipt, String msg){
        try{
            new FeeReceiptDao().insertReceipt(feeReceipt);
            return true;
        }
        catch(DaoException e){
            System.out.println(e.getMessage());
            msg = e.getMessage();
            return false;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
