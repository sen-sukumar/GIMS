package com.gims.model.dao;
import com.gims.model.dto.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
/**
 * @author sukumar sen
 */
public class CourseDao extends BasicDao{
    public static final String INSERT_SQL = "insert into course (name, duration, fee, exam_fee, university_id, category_id)"+
                                            "VALUES(?, ?, ?, ?, (select id from university where name=?), (select id from category where name=?))";
    public static final String UPDATE_SQL = "update course set name=?, duration=?, fee=?, exam_fee=? where name = ? and "+
                                            "university_id=(select id from university where name=?) and "+
                                            "category_id=(select id from category where name=?)";
    public static final String DELETE_SQL = "delete course where name=? and university_id=(select id fro university where name=?) "+
                                            " and category_name=?";
    public static final String SELECT_SQL = "select course.id, course.name, duration, fee, exam_fee from course,university,category " +
                                            "where course.university_id = university.id and course.category_id = category.id " +
                                            " and university.name = ? and category.name = ?";
    public void insert(String university, String categoty, Course course) throws SQLException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(INSERT_SQL);
            try {
                ps.setString(1, course.getName());
                ps.setInt(2, course.getDurationYrs());
                ps.setInt(3, course.getFee());
                ps.setInt(4, course.getExamFee());
                ps.setString(5, university);
                ps.setString(6, categoty);
                ps.executeUpdate();
            }
            finally{
                closeStatement(ps);
            }
        }
        finally{
            closeConnection(con);
        }
    }
    public void read(String university, String category, List<Course> courseList) throws SQLException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(SELECT_SQL);
            try{
                ps.setString(1, university);
                ps.setString(2, category);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String name = rs.getString("name");
                    int duration = rs.getInt("duration");
                    duration = rs.wasNull() ? 0 : duration;
                    int fee = rs.getInt("fee");
                    fee = rs.wasNull() ? 0 : fee;
                    int examFee = rs.getInt("exam_fee");
                    examFee = rs.wasNull() ? 0 : examFee;
                    int courseId = rs.getInt("id");
                    Course course = new Course(name, duration, fee, examFee, courseId);
                    courseList.add(course);
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
    public void update(Course course, String oldCourseName, String university, String category) throws SQLException, DaoException{
        Connection con = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(UPDATE_SQL);
            try{
                ps.setString(1, course.getName());
                if(course.getDurationYrs() != 0){
                    ps.setInt(2, course.getDurationYrs());
                }
                else{
                    ps.setNull(2, Types.INTEGER);
                }
                if(course.getFee() != 0){
                    ps.setInt(3, course.getFee());
                }
                else{
                    ps.setNull(3, Types.INTEGER);
                }
                if(course.getExamFee() != 0){
                    ps.setInt(4, course.getExamFee());
                }
                else{
                    ps.setNull(4, Types.INTEGER);
                }
                ps.setString(5, oldCourseName);
                ps.setString(6, university);
                ps.setString(7, category);
                if(ps.executeUpdate() != 1){
                    throw new DaoException("failed to update");
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
    public void delete(String course, String university, String category) throws SQLException, DaoException{
        Connection con  = getConnection();
        try{
            PreparedStatement ps = con.prepareStatement(DELETE_SQL);
            try{
                ps.setString(1, course);
                ps.setString(2, university);
                ps.setString(3, category);
                if(ps.executeUpdate() != 1){
                    throw new DaoException("Failed to delete");
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
}
