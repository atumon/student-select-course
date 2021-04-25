package com.spring.scs.scssystem.domain;

public class TeacherNamed {
    private String CourseId;
    private String CourseName;
    private String StudentSex;
    private String StudentId;
    private String StudentName;
    private int Score;

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
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

    public String getStudentSex() {
        return StudentSex;
    }

    public void setStudentSex(String studentSex) {
        StudentSex = studentSex;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    @Override
    public String toString() {
        return "TeacherNamed{" +
                "CourseId='" + CourseId + '\'' +
                ", CourseName='" + CourseName + '\'' +
                ", StudentSex='" + StudentSex + '\'' +
                ", StudentId='" + StudentId + '\'' +
                ", StudentName='" + StudentName + '\'' +
                '}';
    }
}
