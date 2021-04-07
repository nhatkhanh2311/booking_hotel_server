package com.example.hotelbooking.BUSINESS_SERVICE.service;

import java.util.ArrayList;

import com.example.hotelbooking.DAO.model.GioiTinh;


public interface GioiTinhService {
	void save(GioiTinh gioiTinh);
	boolean checkTenGioiTinh(String tenGioiTinh);
	ArrayList<GioiTinh> getDanhSachGioiTinh();
}
