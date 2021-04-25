package com.spring.scs.scssystem.domain;

import java.util.Date;

public class Teacher {
    private String OldTeacherId;
    private String TeacherId;
    private String TeacherName;
    private String TeacherPhone;
    private String TeacherOffice;
    private String TeacherDept;
    private String TeacherPassword;
    private String TeacherSex;
    private String TeacherTitle ;
    private String TeacherPoliticalRole ;
    private String TeacherNational ;
    private String TeacherSignDate ;





    public Teacher() {
    }

    public Teacher(String teacherId, String teacherName, String teacherPhone, String teacherOffice, String teacherDept, String teacherPassword) {
        TeacherId = teacherId;
        TeacherName = teacherName;
        TeacherPhone = teacherPhone;
        TeacherOffice = teacherOffice;
        TeacherDept = teacherDept;
        TeacherPassword = teacherPassword;
    }

    public Teacher(String teacherId, String teacherName, String teacherPhone, String teacherOffice, String teacherDept, String teacherPassword, String teacherSex) {
        TeacherId = teacherId;
        TeacherName = teacherName;
        TeacherPhone = teacherPhone;
        TeacherOffice = teacherOffice;
        TeacherDept = teacherDept;
        TeacherPassword = teacherPassword;
        TeacherSex = teacherSex;
    }

    public String getTeacherTitle() {
        return TeacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        TeacherTitle = teacherTitle;
    }

    public String getTeacherPoliticalRole() {
        return TeacherPoliticalRole;
    }

    public void setTeacherPoliticalRole(String teacherPoliticalRole) {
        TeacherPoliticalRole = teacherPoliticalRole;
    }

    public String getTeacherNational() {
        return TeacherNational;
    }

    public void setTeacherNational(String teacherNational) {
        TeacherNational = teacherNational;
    }

    public String getTeacherSignDate() {
        return TeacherSignDate;
    }

    public void setTeacherSignDate(String teacherSignDate) {
        TeacherSignDate = teacherSignDate;
    }

    public String getOldTeacherId() {
        return OldTeacherId;
    }

    public void setOldTeacherId(String oldTeacherId) {
        OldTeacherId = oldTeacherId;
    }

    public String getTeacherSex() {
        return TeacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        TeacherSex = teacherSex;
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

    public String getTeacherPhone() {
        return TeacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        TeacherPhone = teacherPhone;
    }

    public String getTeacherOffice() {
        return TeacherOffice;
    }

    public void setTeacherOffice(String teacherOffice) {
        TeacherOffice = teacherOffice;
    }

    public String getTeacherDept() {
        return TeacherDept;
    }

    public void setTeacherDept(String teacherDept) {
        TeacherDept = teacherDept;
    }

    public String getTeacherPassword() {
        return TeacherPassword;
    }

    public void setTeacherPassword(String teacherPassword) {
        TeacherPassword = teacherPassword;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "OldTeacherId='" + OldTeacherId + '\'' +
                ", TeacherId='" + TeacherId + '\'' +
                ", TeacherName='" + TeacherName + '\'' +
                ", TeacherPhone='" + TeacherPhone + '\'' +
                ", TeacherOffice='" + TeacherOffice + '\'' +
                ", TeacherDept='" + TeacherDept + '\'' +
                ", TeacherPassword='" + TeacherPassword + '\'' +
                ", TeacherSex='" + TeacherSex + '\'' +
                '}';
    }
}
