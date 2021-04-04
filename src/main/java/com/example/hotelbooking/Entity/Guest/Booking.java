package com.example.hotelbooking.Entity.Guest;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import com.example.hotelbooking.Entity.Guest.RoomBooking;
import com.example.hotelbooking.Entity.Guest.Guest;


@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "dateFrom")
    private Date dateFrom;
    @Column(name = "dateTo")
    private Date dateTo;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER)
    private List<RoomBooking> roomBookings;


    @ManyToOne
    @JoinColumn(name = "guestId")
    private com.example.hotelbooking.Entity.Guest.Guest guest;

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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
