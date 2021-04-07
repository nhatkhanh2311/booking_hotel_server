package com.example.hotelbooking.BUSINESS_SERVICE.service;

import com.example.hotelbooking.DAO.model.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface NguoiDungService {
	void save(NguoiDung nguoiDung);
	NguoiDung findByDeletedFalse(long maNguoiDung);
	Page<NguoiDung> findByDeletedFalse(Pageable pageable);
}
