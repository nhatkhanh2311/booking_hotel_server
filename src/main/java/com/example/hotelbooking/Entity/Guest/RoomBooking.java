package com.example.hotelbooking.Entity.Guest;

import com.example.hotelbooking.Entity.hotel.Hotel;
import com.example.hotelbooking.Entity.room.RoomType;
import com.example.hotelbooking.Entity.Guest.Booking;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "roomBooking")
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private int bookingId;
    @Column(name = "dateBookingFrom")
    private Date dateBookingFrom;
    @Column(name = "dateBookingTo")
    private Date dateBookingTo;

    @ManyToOne
    @JoinColumn(name = "booking")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "roomTypeBooking")
    private RoomType roomTypeBooking;

    @ManyToOne
    @JoinColumn(name = "hotelBooking")
    private Hotel hotelBooking;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getDateBookingFrom() {
        return dateBookingFrom;
    }

    public void setDateBookingFrom(Date dateBookingFrom) {
        this.dateBookingFrom = dateBookingFrom;
    }

    public Date getDateBookingTo() {
        return dateBookingTo;
    }

    public void setDateBookingTo(Date dateBookingTo) {
        this.dateBookingTo = dateBookingTo;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public RoomType getRoomTypeBooking() {
        return roomTypeBooking;
    }

    public void setRoomTypeBooking(RoomType roomTypeBooking) {
        this.roomTypeBooking = roomTypeBooking;
    }

    public Hotel getHotelBooking() {
        return hotelBooking;
    }

    public void setHotelBooking(Hotel hotelBooking) {
        this.hotelBooking = hotelBooking;
    }
}
