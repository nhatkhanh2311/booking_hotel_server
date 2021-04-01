package com.example.hotelbooking.Entity.hotel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "countryName")
    private String countryName;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private List<City> cityCountry;



    public List<City> getCityCountry() {
        return cityCountry;
    }

    public void setCityCountry(List<City> cityCountry) {
        this.cityCountry = cityCountry;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
