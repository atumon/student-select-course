package com.spring.scs.scssystem.dao.Admin;

import com.spring.scs.scssystem.domain.Admin;
import com.spring.scs.scssystem.domain.Course;
import com.spring.scs.scssystem.domain.Student;
import com.spring.scs.scssystem.domain.Teacher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;


import java.util.List;


@Mapper
public interface AdminDao {
    //管理课程页面
    @Select("select * from admin where AdminId = #{AdminId} and AdminPassword = #{Password}")
    Admin FindIfAdminExit(@Param("AdminId") String AdminId, @Param("Password") String Password);

    @Select("select * from selectcourseview")
    List<Course> FindAllCourse();

    @Select("Select * from selectcourseview where CourseId = #{CourseId}")
    Course FindOldCourseInfo(@Param("CourseId") String CourseId);

    @Update("update course set CourseId = #{CourseId} , CourseName = #{CourseName} ,CourseCapacity = #{CourseCapacity} ,CoursePoint=#{CoursePoint}, CourseDept=#{CourseDept}, CourseAddress=#{CourseAddress}, CourseTeacherId=#{TeacherId} where CourseId = #{OldCourseId}")
    void UpdateAllByOldCid(Course course);


    @Select("select * from selectcourseview where CourseId = #{CourseId}")
    Course FindByCourseId(@Param("CourseId") String CourseId);

    @Delete("delete from course where CourseId = #{CourseId}")
    void DeleteCourse(@Param("CourseId") String CourseId);

    @Insert({ "insert into course (CourseId, CourseName, CourseCapacity, CoursePoint, CourseDept, CourseAddress, CourseTeacherId) values(#{CourseId}, #{CourseName}, #{CourseCapacity}, #{CoursePoint}, #{CourseDept}, #{CourseAddress}, #{TeacherId})" })
    void InsertCourse(Course course);

    //管理学生页面
    @Select("Select * from student")
    List<Student> FindAllStudent();

    @Update({"update student set StudentId = #{StudentId} , StudentName = #{StudentName} ,StudentPassword = #{StudentPassword} ,StudentDept=#{StudentDept}, StudentSex=#{StudentSex} where StudentId = #{OldStudentId}"})
    void UpadteAllBySid(Student student);

    @Delete("delete from student where StudentId = #{StudentId}")
    void DelecteStudentByStudentId(@Param("StudentId") String StudentId);

    @Delete("delete from selectcourse where SelectCourseSId = #{StudentId}")
    void DelecteSelectCourseByStudentId(@Param("StudentId") String StudentId);

    @Insert({ "insert into student (StudentId, StudentName, StudentPassword, StudentDept, StudentSex, StudentSignYear, StudentMajor, StudentNational) values(#{StudentId}, #{StudentName}, #{StudentPassword}, #{StudentDept}, #{StudentSex}, #{StudentSignYear}, #{StudentMajor}, #{StudentNational})" })
    int InsertRoleStudent(Student student);

    @Select("Select * from student where StudentId = #{StudentId}")
    Student FindByStudentId(@Param("StudentId") String StudentId);

    //管理教师页面
    @Select("select * from teacher")
    List<Teacher> FindAllTeacher();

    @Select("Select * from teacher where TeacherId = #{TeacherId}")
    Teacher FindByTeacherId(@Param("TeacherId") String TeacherId);

    @Update({"update teacher set TeacherId = #{TeacherId} , TeacherName = #{TeacherName} ,TeacherPassword = #{TeacherPassword} ,TeacherDept=#{TeacherDept}, TeacherSex=#{TeacherSex}, TeacherPhone=#{TeacherPhone}, TeacherOffice=#{TeacherOffice} where TeacherId = #{OldTeacherId}"})
    void UpdateAllByTid(Teacher teacher);

    @Delete("delete from teacher where TeacherId = #{TeacherId}")
    void DelectTeacherByTeacherId(@Param("TeacherId") String TeacherId);

    @Delete("delete from teachcourse where TeachCourseTid = #{TeacherId}")
    void DeleteTeachCourseByTeacherId(@Param("TeacherId") String TeacherId);

    @Insert({ "insert into teacher (TeacherId, TeacherName, TeacherPassword, TeacherPhone, TeacherOffice, TeacherDept, TeacherSex) values(#{TeacherId}, #{TeacherName}, #{TeacherPassword}, #{TeacherPhone}, #{TeacherOffice}, #{TeacherDept}, #{TeacherSex})" })
    int InsertRoleTeacher(Teacher teacher);

    //管理选课界面
    @Select({ "call prc_beginselectcourse" })
//    @Options(statementType = StatementType.CALLABLE)
    void StartSelect();

    @Select({ "call prc_endselectcourse" })
//    @Options(statementType = StatementType.CALLABLE)
    void EndSelect();

    @Select({ "call  prc_begininputscore" })
//    @Options(statementType = StatementType.CALLABLE)
    void StartInput();

    @Select({ "call  prc_endinputscore" })
//    @Options(statementType = StatementType.CALLABLE)
    void EndInput();

    @Select("select AllowSelectCourse from state")
    int getSelectState();

    @Select("select AllowInputScore from state")
    int getInutState();

}
