package com.example.demo.entity;

import java.time.LocalDate;



import java.time.LocalDate;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class BookingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Room room;

	
//	@Column(columnDefinition = "DATE")

	private LocalDate start;
	
//	@Column(columnDefinition = "DATE")



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
