package com.example.demo.entity;
<<<<<<< HEAD
import java.time.LocalDate;

=======

<<<<<<< HEAD
=======
>>>>>>> origin/master

import java.time.LocalDate;

>>>>>>> 1bd560a66f42a3ad9fa7014d70b76141abf05dd0
import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class BookingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Room room;
<<<<<<< HEAD
	
//	@Column(columnDefinition = "DATE")

	private LocalDate start;
	
//	@Column(columnDefinition = "DATE")
=======

	private LocalDate start;
>>>>>>> origin/master

	private LocalDate end;
	
	@OneToOne
	private User host;
	
	//======================

	public long getId() {
		return id;
	}

	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}
}
