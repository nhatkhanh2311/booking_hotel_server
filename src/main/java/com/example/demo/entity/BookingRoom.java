package com.example.demo.entity;

//import org.joda.time.LocalDate;

import javax.persistence.*;
import java.util.Date;
//import java.time.LocalDate;

@Entity
public class BookingRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Room room;
	
//	@Column(columnDefinition = "DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	
//	@Column(columnDefinition = "DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
