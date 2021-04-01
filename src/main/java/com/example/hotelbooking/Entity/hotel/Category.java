package com.example.demo.Entity.hotel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "categoryName")
    private String categoryName;

    @OneToMany(mappedBy = "categoryOfHotel", fetch = FetchType.EAGER)
    private List<Hotel> cateHotels;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Hotel> getCateHotels() {
        return cateHotels;
    }

    public void setCateHotels(List<Hotel> cateHotels) {
        this.cateHotels = cateHotels;
    }
}
