package com.example.hotelbooking.BUSINESS_SERVICE.implement;

import java.util.ArrayList;
import java.util.Optional;

import com.example.hotelbooking.BUSINESS_SERVICE.service.GioiTinhService;
import com.example.hotelbooking.DAO.model.GioiTinh;
import com.example.hotelbooking.DAO.repository.GioiTinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GioiTinhServiceImpl implements GioiTinhService {
	@Autowired
	private GioiTinhRepository gioiTinhRepository;
	public void save(GioiTinh gioiTinh) {
		gioiTinhRepository.save(gioiTinh);
	}

	public boolean checkTenGioiTinh(String tenGioiTinh) {
		GioiTinh gioiTinh = gioiTinhRepository.findByTenGioiTinh(tenGioiTinh);
		if (gioiTinh != null) return true;
		return false;
	}

	public ArrayList<GioiTinh> getDanhSachGioiTinh() {
		return gioiTinhRepository.findAll();
	}

}
