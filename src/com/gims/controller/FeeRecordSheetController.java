package com.gims.controller;
import com.gims.model.dto.Admission;
import com.gims.model.dto.Course;
import com.gims.model.dto.University;
import java.util.List;

/**
 * @author sukumar sen
 */
public class FeeRecordSheetController {
    public boolean loadUniversities(List<University> universityList){
        return new UniversityController().loadUniversities(universityList);
    }
    public boolean loadCategories(List<String> categoryList){
        return new CategoryController().loadCategories(categoryList);
    }
    public boolean loadAdmissions(List<Admission> admissionList){
        return new AdmissionController().loadAdmissionsActiveWithDetailWithFee(admissionList);
    }
    public boolean loadAdmissions(int courseId, List<Admission> admissionList){
        return new AdmissionController().loadAdmissionsActiveWithDetailWithFee(courseId, admissionList);
    }
    public boolean loadCourses(String university, String category, List<Course> courseList){
        return new CourseController().loadCourses(university, category, courseList);
    }
}
