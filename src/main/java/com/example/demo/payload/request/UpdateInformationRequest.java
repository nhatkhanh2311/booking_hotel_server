package com.example.demo.payload.request;

import java.time.LocalDate;

public class UpdateInformationRequest {
    private String password;
    private String nameUserDetail;
    private LocalDate birth;
    private String phoneNumber;

    public UpdateInformationRequest() {
    }

    public UpdateInformationRequest(String password, String nameUserDetail, LocalDate birth, String phoneNumber) {
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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
