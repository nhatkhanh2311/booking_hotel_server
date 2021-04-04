package com.example.hotelbooking.Entity.room;
import com.example.hotelbooking.Entity.room.Room;

import com.example.hotelbooking.Entity.Guest.RoomBooking;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roomType")
public class RoomType {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "typeName")
    private String typrName;
    @Column(name = "numberOfGuest")
    private int numberOfGuest;

    @OneToMany(mappedBy = "roomType", fetch = FetchType.EAGER)
    private List<Room> rooms;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "roomTypeBooking")
    private  List<RoomBooking> roomBookings;



    public List<RoomBooking> getRoomBookings() {
        return roomBookings;
    }

    public void setRoomBookings(List<RoomBooking> roomBookings) {
        this.roomBookings = roomBookings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTyprName() {
        return typrName;
    }

    public void setTyprName(String typrName) {
        this.typrName = typrName;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(int numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
