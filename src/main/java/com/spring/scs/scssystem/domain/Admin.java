package com.spring.scs.scssystem.domain;

import java.util.Date;

public class Admin {
    private Integer AdminId;
    private String AdminName;
    private String Password;
    private String AdminPhone;
    private String AdminOffice;

    public Admin() {
    }

    public Admin(Integer adminId, String adminName, String password, String adminPhone, String adminOffice) {
        AdminId = adminId;
        AdminName = adminName;
        Password = password;
        AdminPhone = adminPhone;
        AdminOffice = adminOffice;
    }

    public Integer getAdminId() {
        return AdminId;
    }

    public void setAdminId(Integer adminId) {
        AdminId = adminId;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAdminPhone() {
        return AdminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        AdminPhone = adminPhone;
    }

    public String getAdminOffice() {
        return AdminOffice;
    }

    public void setAdminOffice(String adminOffice) {
        AdminOffice = adminOffice;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "AdminId=" + AdminId +
                ", AdminName='" + AdminName + '\'' +
                ", Password='" + Password + '\'' +
                ", AdminPhone='" + AdminPhone + '\'' +
                ", AdminOffice='" + AdminOffice + '\'' +
                '}';
    }
}
