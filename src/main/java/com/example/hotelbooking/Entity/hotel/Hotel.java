package com.example.hotelbooking.Entity.hotel;

import com.example.hotelbooking.Entity.Guest.RoomBooking;
import com.example.hotelbooking.Entity.room.Room;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "hotelName")
    private String discription;
    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "address")
    private String address;

    @Column(name = "postCode")
    private String postCode;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category categoryOfHotel;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City cityId;

    @ManyToOne
    @JoinColumn(name = "directorId")
    private HotelDirector directorId;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "hotels")
    private List<Room> rooms;

    @OneToMany(mappedBy = "hotelBooking")
    private List<RoomBooking> roomBookings;

    public List<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(List<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
    }

    public Hotel() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Category getCategoryOfHotel() {
        return categoryOfHotel;
    }

    public void setCategoryOfHotel(Category categoryOfHotel) {
        this.categoryOfHotel = categoryOfHotel;
    }


    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public HotelDirector getDirectorId() {
        return directorId;
    }

    public void setDirectorId(HotelDirector directorId) {
        this.directorId = directorId;
    }
}
