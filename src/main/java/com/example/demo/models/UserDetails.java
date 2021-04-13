package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "userDetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @Lob
    @Column(name = "avatar", nullable = true)
    private byte[] avatar;


    @Column(name = "nameUserDetails", nullable = true)
    private String nameUserDetails;


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

    public UserDetails() {
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

    public String getNameUserDetails() {
        return nameUserDetails;
    }

    public void setNameUserDetails(String nameUserDetails) {
        this.nameUserDetails = nameUserDetails;
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
