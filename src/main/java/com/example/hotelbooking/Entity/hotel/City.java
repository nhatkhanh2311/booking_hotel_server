package com.example.hotelbooking.Entity.hotel;

import com.example.hotelbooking.Entity.hotel.Country;
import com.example.hotelbooking.Entity.hotel.BeautySpot;
import com.example.hotelbooking.Entity.hotel.Hotel;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "cityName")
    private String cityName;


    @ManyToOne
    @JoinColumn(name = "countryId")
    private Country country;



    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<BeautySpot> beautySpot;


    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "cityId")
    private List<Hotel> cityHotels;



    public List<Hotel> getCityHotels() {
        return cityHotels;
    }

    public void setCityHotels(List<Hotel> cityHotels) {
        this.cityHotels = cityHotels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<BeautySpot> getBeautySpot() {
        return beautySpot;
    }

    public void setBeautySpot(List<BeautySpot> beautySpot) {
        this.beautySpot = beautySpot;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
