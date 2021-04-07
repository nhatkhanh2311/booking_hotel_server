package com.example.hotelbooking.BUSINESS_SERVICE.implement;

import java.util.ArrayList;

import com.example.hotelbooking.BUSINESS_SERVICE.service.QuyenService;
import com.example.hotelbooking.DAO.model.DacQuyen;
import com.example.hotelbooking.DAO.repository.QuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class QuyenServiceImpl implements QuyenService {
	@Autowired
	private QuyenRepository quyenRepository;

	public ArrayList<DacQuyen> getDanhSachDacQuyen() {
		return quyenRepository.findAll();
	}

	public void save(DacQuyen dacQuyen) {
		quyenRepository.save(dacQuyen);
	}

	public boolean checkTenDacQuyen(String tenDacQuyen) {
		DacQuyen dacQuyen = quyenRepository.findByTenDacQuyen(tenDacQuyen);
		if (dacQuyen == null) return false;
		return true;
	}

}
