package com.example.hotelbooking.BUSINESS_SERVICE.service;

import com.example.hotelbooking.BUSINESS_SERVICE.exception.TaiKhoanNotFoundException;
import com.example.hotelbooking.DAO.model.TaiKhoan;



public interface TaiKhoanService {
	TaiKhoan findByMaTaiKhoanAndDeletedFalse(Long maTaiKhoan);
	TaiKhoan save(TaiKhoan taiKhoan);
	boolean existsById(long id);
	boolean existsByTenDangNhap(String tenDangNhap);
	TaiKhoan findByMaTaiKhoan(long id);
	TaiKhoan findTopByMaTaiKhoanDesc();
	TaiKhoan findByTenDangNhapAndMatKhau(String tenDangNhap,String matKhau);
	boolean lockTaiKhoan(long id) throws TaiKhoanNotFoundException;
	boolean unlockTaiKhoan(long id) throws TaiKhoanNotFoundException;
	boolean changPassword(long id, String matKhau) throws TaiKhoanNotFoundException;
}
