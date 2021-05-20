package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "userDetail")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @Lob
    @Column(name = "avatar", nullable = true)
    private byte[] avatar;


    @Column(name = "nameUserDetail", nullable = true)
    private String nameUserDetail;


    @Column(name = "phoneNumber", nullable = true)
    private String phoneNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Birth", nullable = true)
    private LocalDate birth;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    public long getId() {
        return id;
    }

    public UserDetail() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

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

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
