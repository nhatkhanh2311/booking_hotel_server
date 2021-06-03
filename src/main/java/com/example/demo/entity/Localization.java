package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Localization {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String country;
	
	private String city;
	
	private String street;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "hotelId")
	@JsonBackReference
	private Hotel hotel;

	public Localization() {	}

	public Localization(String country, String city, String street) {
		this.country = country;
		this.city = city;
		this.street = street;
//		this.hotel = hotel;
	}

	//-------------------------

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	@Override
	public String toString() {
		return "Localization [id=" + id + ", country=" + country + ", city=" + city + ", street=" + street + ", hotel="
				+ hotel + "";
	}
}
