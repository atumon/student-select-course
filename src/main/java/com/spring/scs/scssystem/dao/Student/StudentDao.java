package com.spring.scs.scssystem.dao.Student;

import com.spring.scs.scssystem.domain.Course;
import com.spring.scs.scssystem.domain.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentDao {
    //登陆页面
    @Select("select * from student where StudentId = #{StudentId} and StudentPassword = #{StudentPassword}")
    Student FindIfStuExit(@Param("StudentId") String StudentId, @Param("StudentPassword") String StudentPassword);

    //选课页面
    @Select("select * from selectcourseview")
    List<Course> FindAllCourse();

    @Select("select func_getcoursetimechar(#{CourseId})")
    String FindWeekInformation(@Param("CourseId") String CourseId);

    @Select("select CourseRemain from selectcourseview where CourseId = #{CourseId}")
    int FindCourseRemain(@Param("CourseId") String CourseId);

    @Select("select SelectCourseSid from selectcourse where SelectCourseCid = #{CourseId} and SelectCourseSid = #{StudentId}")
    String FindIfStudentSelect(@Param("CourseId") String CourseId, @Param("StudentId") String StudentId);

    @Select("select func_selectcourse(#{StudentId},#{CourseId})")
    int InsertSelectCourse(@Param("StudentId") String StudentId, @Param("CourseId") String CourseId);

    @Delete("delete from selectcourse where SelectCourseCid = #{CourseId} and SelectCourseSid = #{StudentId}")
    void DelSelectCourse(@Param("CourseId") String CourseId, @Param("StudentId") String StudentId);

   //学生信息页面
    @Select("Select * from student where StudentId = #{StudentId}")
    Student FindByStudentId(@Param("StudentId") String StudentId);


    //学生课表页面
    @Select("select CourseName from coursetable where StudentId = #{StudentId} and Week=#{Week} and Class=#{Class}")
    String FindStudentCourseName(@Param("StudentId") String StudentId, @Param("Week") int Week, @Param("Class") int Class);

    @Select("select TeacherName from coursetable where StudentId = #{StudentId} and Week=#{Week} and Class=#{Class}")
    String FindTeacherName(@Param("StudentId") String StudentId, @Param("Week") int Week, @Param("Class") int Class);

    @Select("select CourseAddress from coursetable where StudentId = #{StudentId} and Week=#{Week} and Class=#{Class}")
    String FindSCourseAddress(@Param("StudentId") String StudentId, @Param("Week") int Week, @Param("Class") int Class);


}
