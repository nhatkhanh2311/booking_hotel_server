package com.example.demo.payload.request;

import java.util.Date;

public class UpdateInformationRequest {
    private String password;
    private String nameUserDetail;
    private Date birth;
    private String phoneNumber;

    public UpdateInformationRequest() {
    }

    public UpdateInformationRequest(String password, String nameUserDetail, Date birth, String phoneNumber) {
        this.password = password;
        this.nameUserDetail = nameUserDetail;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNameUserDetail() {
        return nameUserDetail;
    }

    public void setNameUserDetail(String nameUserDetail) {
        this.nameUserDetail = nameUserDetail;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
