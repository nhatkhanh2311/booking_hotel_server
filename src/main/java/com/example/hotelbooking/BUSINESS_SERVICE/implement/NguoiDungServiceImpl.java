package com.example.hotelbooking.BUSINESS_SERVICE.implement;

import java.util.Optional;

import com.example.hotelbooking.BUSINESS_SERVICE.service.NguoiDungService;
import com.example.hotelbooking.DAO.model.NguoiDung;
import com.example.hotelbooking.DAO.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class NguoiDungServiceImpl implements NguoiDungService {
	@Autowired
	private NguoiDungRepository nguoiDungRepository;

	public void save(NguoiDung nguoiDung) {
		nguoiDungRepository.save(nguoiDung);
	}

	public NguoiDung findByDeletedFalse(long maNguoiDung) {
		Optional<NguoiDung> optional = nguoiDungRepository.findById(maNguoiDung);
		if (!optional.isPresent()) return null;
		return nguoiDungRepository.findById(maNguoiDung).get();
	}

	public Page<NguoiDung> findByDeletedFalse(Pageable pageable) {
		return nguoiDungRepository.findAll(pageable);
	}
}
