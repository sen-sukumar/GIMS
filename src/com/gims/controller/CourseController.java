package com.gims.controller;
import com.gims.model.dao.CourseDao;
import com.gims.model.dao.DaoException;
import com.gims.model.dto.Course;
import com.gims.model.dto.University;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
/**
 * @author sukumar sen
 */
public class CourseController {
    public boolean initialize(List<University> universityList, List<String> categoryList){
        boolean loadSuccess = new UniversityController().loadUniversities(universityList) &&
                              new CategoryController().loadCategories(categoryList);
        return loadSuccess;
    }
    public boolean loadCourses(String university, String category, List<Course> courseList){
        try{
            new CourseDao().read(university, category, courseList);
            Collections.sort(courseList);
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }
    public boolean addCourse(String university, String category, Course course){
        try{
            new CourseDao().insert(university, category, course);
        }
        catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }
        return true;
    }
    public boolean editCourse(Course course, String oldCourseName, String university, String category){
        try{
            new CourseDao().update(course, oldCourseName, university, category);
        }
        catch(SQLException | DaoException e){
            return false;
        }
        return true;
    }
    public boolean removeCourse(String name, String university, String category){
        try{
            new CourseDao().delete(name,university,category);
        }
        catch(SQLException | DaoException e){
            return false;
        }
        return true;
    }
}
