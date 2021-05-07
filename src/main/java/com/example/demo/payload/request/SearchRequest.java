package com.example.demo.payload.request;

import java.time.LocalDate;
import java.util.Date;

public class SearchRequest {
    private String cityName;
    private Date start;
    private Date end;
    private int capacity;

    public SearchRequest() {
    }

    public SearchRequest(String cityName, Date start, Date end, int capacity) {
        this.cityName = cityName;
        this.start = start;
        this.end = end;
        this.capacity = capacity;
    }

    public String getHotelName() {
        return cityName;
    }

    public void setHotelName(String cityName) {
        this.cityName = cityName;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
