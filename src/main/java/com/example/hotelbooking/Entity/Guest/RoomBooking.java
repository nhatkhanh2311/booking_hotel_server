package com.example.demo.Entity.Guest;

import com.example.demo.Entity.hotel.Hotel;
import com.example.demo.Entity.room.RoomType;

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




}
