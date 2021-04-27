package com.example.demo.payload.request;

import com.example.demo.entity.Localization;

public class HotelRequest {
    public String name;
    public Localization localization;

    public HotelRequest(String name, Localization localization) {
        this.name = name;
        this.localization = localization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }
}
