package com.spring.scs.scssystem.domain;

public class Course {

    private String OldCourseId;
    private String OldTeacherId;
    private String CourseId;
    private String CourseName;
    private int CourseCapacity;
    private int CourseAmount;
    private float CoursePoint;
    private int CourseDept;
    private int CourseRemain;
    private String CourseAddress;
    private String TeacherId;
    private String TeacherName;


    public Course() {
    }

    public Course(String courseId, String courseName, int courseCapacity, int courseAmount, float coursePoint, int courseDept) {
        CourseId = courseId;
        CourseName = courseName;
        CourseCapacity = courseCapacity;
        CourseAmount = courseAmount;
        CoursePoint = coursePoint;
        CourseDept = courseDept;
    }

    public Course(String courseId, String courseName, int courseCapacity, int courseAmount, float coursePoint, int courseDept, String courseAddress, String teacherId, String teacherName) {
        CourseId = courseId;
        CourseName = courseName;
        CourseCapacity = courseCapacity;
        CourseAmount = courseAmount;
        CoursePoint = coursePoint;
        CourseDept = courseDept;
        CourseAddress = courseAddress;
        TeacherId = teacherId;
        TeacherName = teacherName;
    }

    public int getCourseRemain() {
        return CourseRemain;
    }

    public void setCourseRemain(int courseRemain) {
        CourseRemain = courseRemain;
    }

    public String getOldTeacherId() {
        return OldTeacherId;
    }

    public void setOldTeacherId(String oldTeacherId) {
        this.OldTeacherId = oldTeacherId;
    }

    public String getOldCourseId() {
        return OldCourseId;
    }

    public void setOldCourseId(String oldCourseId) {
        OldCourseId = oldCourseId;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public int getCourseCapacity() {
        return CourseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        CourseCapacity = courseCapacity;
    }

    public int getCourseAmount() {
        return CourseAmount;
    }

    public void setCourseAmount(int courseAmount) {
        CourseAmount = courseAmount;
    }

    public float getCoursePoint() {
        return CoursePoint;
    }

    public void setCoursePoint(float coursePoint) {
        CoursePoint = coursePoint;
    }

    public int getCourseDept() {
        return CourseDept;
    }

    public void setCourseDept(int courseDept) {
        CourseDept = courseDept;
    }

    public String getTeacherId() {
        return TeacherId;
    }

    public void setTeacherId(String teacherId) {
        TeacherId = teacherId;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getCourseAddress() {
        return CourseAddress;
    }

    public void setCourseAddress(String courseAddress) {
        CourseAddress = courseAddress;
    }

    @Override
    public String toString() {
        return "Course{" +
                "OldCourseId='" + OldCourseId + '\'' +
                ", OldTeacherId='" + OldTeacherId + '\'' +
                ", CourseId='" + CourseId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", CourseCapacity=" + CourseCapacity +
                ", CourseAmount=" + CourseAmount +
                ", CoursePoint=" + CoursePoint +
                ", CourseDept=" + CourseDept +
                ", CourseRemain=" + CourseRemain +
                ", CourseAddress='" + CourseAddress + '\'' +
                ", TeacherId='" + TeacherId + '\'' +
                ", TeacherName='" + TeacherName + '\'' +
                '}';
    }
}
