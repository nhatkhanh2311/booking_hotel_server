package com.example.hotelbooking.BUSINESS_SERVICE.service;

import com.example.hotelbooking.DAO.model.DacQuyen;

import java.util.ArrayList;
import java.util.List;

public interface QuyenService {
	ArrayList<DacQuyen> getDanhSachDacQuyen();
	void save(DacQuyen dacQuyen);
	boolean checkTenDacQuyen(String tenDacQuyen);
}
