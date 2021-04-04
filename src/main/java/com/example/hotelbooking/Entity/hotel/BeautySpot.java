package com.example.hotelbooking.Entity.hotel;

import javax.persistence.*;

@Entity
@Table(name = "beautySpot")
public class BeautySpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "nameSpot")
    private String nameSpot;
    @Column(name = "photoSpot")
    private String photoSpot;
    @Column(name = "descriptionSpot")
    private String descriptionSpot;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSpot() {
        return nameSpot;
    }

    public void setNameSpot(String nameSpot) {
        this.nameSpot = nameSpot;
    }

    public String getPhotoSpot() {
        return photoSpot;
    }

    public void setPhotoSpot(String photoSpot) {
        this.photoSpot = photoSpot;
    }

    public String getDescriptionSpot() {
        return descriptionSpot;
    }

    public void setDescriptionSpot(String descriptionSpot) {
        this.descriptionSpot = descriptionSpot;
    }
}
