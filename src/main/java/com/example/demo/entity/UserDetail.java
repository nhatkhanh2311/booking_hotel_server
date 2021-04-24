package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userDetail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @JsonIgnore
//    @Lob
//    @Column(name = "avatar", nullable = true)
//    private byte[] avatar;


    @Column(name = "nameUserDetail", nullable = true)
    private String nameUserDetail;


    @Column(name = "phoneNumber", nullable = true)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Birth", nullable = true)
    private Date birth;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

//    public byte[] getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(byte[] avatar) {
//        this.avatar = avatar;
//    }

    public String getNameUserDetail() {
        return nameUserDetail;
    }

    public void setNameUserDetail(String nameUserDetail) {
        this.nameUserDetail = nameUserDetail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
