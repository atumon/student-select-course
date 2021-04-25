package com.spring.scs.scssystem.dao.Teacher;

import com.spring.scs.scssystem.domain.Teacher;
import com.spring.scs.scssystem.domain.TeacherNamed;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface TeacherDao {

    //登陆页面
    @Select("select * from teacher where TeacherId = #{TeacherId} and TeacherPassword = #{TeacherPassword}")
    Teacher FindIfTeacherExit(@Param("TeacherId") String TeacherId, @Param("TeacherPassword") String TeacherPassword);

    //成绩录入页面
    @Select("select * from teachernamed where TeacherId = #{TeacherId}")
    List<TeacherNamed> FindTeacherCourseName(@Param("TeacherId") String TeacherId);

    @Update("update selectcourse set SelectCourseScore = #{grade} where SelectCourseSid = #{StudentId} and SelectCourseCid = #{CourseId}")
    void UpdateStudentGrade(@Param("StudentId") String AdminId, @Param("CourseId") String Password, @Param("grade") int grade);

    //教师信息页面
    @Select("Select * from teacher where TeacherId = #{TeacherId}")
    Teacher FindByTeacherId(@Param("TeacherId") String TeacherId);

    //教师课表
    @Select("select CourseName from coursetable where TeacherId = #{TeacherId} and Week=#{Week} and Class=#{Class}")
    String FindTimeCourseName(@Param("TeacherId") String TeacherId, @Param("Week") int Week, @Param("Class") int Class);

    @Select("select CourseAddress from coursetable where TeacherId = #{TeacherId} and Week=#{Week} and Class=#{Class}")
    String FindTCourseAddress(@Param("TeacherId") String TeacherId, @Param("Week") int Week, @Param("Class") int Class);

}
