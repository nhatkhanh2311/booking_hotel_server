package com.example.hotelbooking.WEB_SERVICE.security.user;

import com.example.hotelbooking.DAO.model.TaiKhoan;
import com.example.hotelbooking.DAO.repository.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserService implements UserDetailsService {
	@Autowired
	private TaiKhoanRepository taiKhoanRepository;

	public UserDetails loadUserByUsername(String tenDangNhap) {
		// Kiểm tra xem taiKhoan có tồn tại trong database không?
		TaiKhoan taiKhoan = taiKhoanRepository.findByTenDangNhap(tenDangNhap);
		if (taiKhoan == null) {
			throw new UsernameNotFoundException(tenDangNhap);
		}
		return new CustomUserDetails(taiKhoan);
	}

	// JWTAuthenticationFilter sẽ sử dụng hàm này
	@Transactional
	public UserDetails loadUserById(Long maTaiKhoan) {
		TaiKhoan taiKhoan = taiKhoanRepository.findById(maTaiKhoan).get();
		if (taiKhoan == null) {
			throw new UsernameNotFoundException("User not found with id : " + maTaiKhoan);
		}
		return new CustomUserDetails(taiKhoan);
	}
}
