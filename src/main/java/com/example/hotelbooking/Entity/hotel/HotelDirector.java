package com.example.hotelbooking.Entity.hotel;

import javax.persistence.*;
import java.util.List;
import com.example.hotelbooking.Entity.hotel.Hotel;


@Entity
@Table(name = "hotelDirector")
public class HotelDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @Column(name = "phone")
    private String phoneNumber;
    @Column(name = "photo")
    private String photo;
    @Column(name = "desription")
    private String description;

    @OneToMany(mappedBy = "directorId", fetch = FetchType.EAGER)
    private List<Hotel> Hotels;

    public List<Hotel> getHotels() {
        return Hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.Hotels = hotels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
