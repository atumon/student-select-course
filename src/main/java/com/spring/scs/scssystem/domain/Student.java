package com.spring.scs.scssystem.domain;

import java.nio.channels.Pipe;

public class Student {
    private String StudentId;
    private String StudentName;
    private String StudentSex;
    private String StudentDept;
    private String StudentPassword;
    private String StudentNational;
    private String StudentSignYear;
    private String StudentMajor;
    private String StudentBirthday;
    private String OldStudentId;


    public Student() {
    }

    public Student(String studentId, String studentName, String studentSex, String studentDept, String studentPassword) {
        StudentId = studentId;
        StudentName = studentName;
        StudentSex = studentSex;
        StudentDept = studentDept;
        StudentPassword = studentPassword;
    }

    public String getStudentNational() {
        return StudentNational;
    }

    public void setStudentNational(String studentNational) {
        StudentNational = studentNational;
    }

    public String getStudentSignYear() {
        return StudentSignYear;
    }

    public void setStudentSignYear(String studentSignYear) {
        StudentSignYear = studentSignYear;
    }

    public String getStudentMajor() {
        return StudentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        StudentMajor = studentMajor;
    }

    public String getStudentBirthday() {
        return StudentBirthday;
    }

    public void setStudentBirthday(String studentBirthday) {
        StudentBirthday = studentBirthday;
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

    public String getStudentSex() {
        return StudentSex;
    }

    public void setStudentSex(String studentSex) {
        StudentSex = studentSex;
    }

    public String getStudentDept() {
        return StudentDept;
    }

    public void setStudentDept(String studentDept) {
        StudentDept = studentDept;
    }

    public String getStudentPassword() {
        return StudentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        StudentPassword = studentPassword;
    }

    public String getOldStudentId() {
        return OldStudentId;
    }

    public void setOldStudentId(String oldStudentId) {
        OldStudentId = oldStudentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId='" + StudentId + '\'' +
                ", StudentName='" + StudentName + '\'' +
                ", StudentSex='" + StudentSex + '\'' +
                ", StudentDept='" + StudentDept + '\'' +
                ", StudentPassword='" + StudentPassword + '\'' +
                ", StudentNational='" + StudentNational + '\'' +
                ", StudentSignYear='" + StudentSignYear + '\'' +
                ", StudentMajor='" + StudentMajor + '\'' +
                ", StudentBirthday='" + StudentBirthday + '\'' +
                ", OldStudentId='" + OldStudentId + '\'' +
                '}';
    }
}
