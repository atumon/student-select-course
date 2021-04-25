package com.spring.scs.scssystem.domain;

import java.security.PrivateKey;
import java.util.PrimitiveIterator;

public class CourseSize {
    private Course course;
    private int leftCourseNumber;
    private String ifSelect;
    private String weekInformation;


    public CourseSize() {
    }

    public CourseSize(Course course, int leftCourseNumber, String ifSelect) {
        this.course = course;
        this.leftCourseNumber = leftCourseNumber;
        this.ifSelect = ifSelect;
    }

    public CourseSize(Course course, int leftCourseNumber, String ifSelect, String weekInformation) {
        this.course = course;
        this.leftCourseNumber = leftCourseNumber;
        this.ifSelect = ifSelect;
        this.weekInformation = weekInformation;
    }

    public String getWeekInformation() {
        return weekInformation;
    }

    public void setWeekInformation(String weekInformation) {
        this.weekInformation = weekInformation;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getLeftCourseNumber() {
        return leftCourseNumber;
    }

    public void setLeftCourseNumber(int leftCourseNumber) {
        this.leftCourseNumber = leftCourseNumber;
    }

    public String getIfSelect() {
        return ifSelect;
    }

    public void setIfSelect(String ifSelect) {
        this.ifSelect = ifSelect;
    }

    @Override
    public String toString() {
        return "CourseSize{" +
                "course=" + course +
                ", leftCourseNumber=" + leftCourseNumber +
                ", ifSelect='" + ifSelect + '\'' +
                '}';
    }
}
