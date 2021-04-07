//package com.example.hotelbooking.Entity.room;
//
//import com.example.hotelbooking.Entity.hotel.Hotel;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "room")
//public class Room {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//    @Column(name = "roomNamr")
//    private String roomName;
//    @Column (name = "description")
//    private String description;
//    @Column(name = "currentPrice")
//    private double currentPrice;
//
//    @ManyToOne
//    @JoinColumn(name = "hotelId")
//    private Hotel hotels;
//
//    @ManyToOne
//    @JoinColumn(name = "roomTypeId")
//    private RoomType roomType;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getRoomName() {
//        return roomName;
//    }
//
//    public void setRoomName(String roomName) {
//        this.roomName = roomName;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public double getCurrentPrice() {
//        return currentPrice;
//    }
//
//    public void setCurrentPrice(double currentPrice) {
//        this.currentPrice = currentPrice;
//    }
//
//    public Hotel getHotels() {
//        return hotels;
//    }
//
//    public void setHotels(Hotel hotels) {
//        this.hotels = hotels;
//    }
//
//    public RoomType getRoomType() {
//        return roomType;
//    }
//
//    public void setRoomType(RoomType roomType) {
//        this.roomType = roomType;
//    }
//}
