package com.example.demo.entity;
<<<<<<< HEAD
import java.time.LocalDate;

=======

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> origin/master

>>>>>>> 353048239e53eb6d379d45b9526152a5dbef6544
import java.time.LocalDate;
import javax.persistence.*;

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
