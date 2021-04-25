package com.spring.scs.scssystem.domain;

public class Schedules {

    private String Time;
    private String Mon;
    private String Tue;
    private String Wed;
    private String Thur;
    private String Fri;

    public Schedules() {
    }

    public Schedules(String time, String mon, String tue, String wed, String thur, String fri) {
        Time = time;
        Mon = mon;
        Tue = tue;
        Wed = wed;
        Thur = thur;
        Fri = fri;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getTue() {
        return Tue;
    }

    public void setTue(String tue) {
        Tue = tue;
    }

    public String getWed() {
        return Wed;
    }

    public void setWed(String wed) {
        Wed = wed;
    }

    public String getThur() {
        return Thur;
    }

    public void setThur(String thur) {
        Thur = thur;
    }

    public String getFri() {
        return Fri;
    }

    public void setFri(String fri) {
        Fri = fri;
    }

    @Override
    public String toString() {
        return "Schedules{" +
                "Time='" + Time + '\'' +
                ", Mon='" + Mon + '\'' +
                ", Tue='" + Tue + '\'' +
                ", Wed='" + Wed + '\'' +
                ", Thur='" + Thur + '\'' +
                ", Fri='" + Fri + '\'' +
                '}';
    }
}
