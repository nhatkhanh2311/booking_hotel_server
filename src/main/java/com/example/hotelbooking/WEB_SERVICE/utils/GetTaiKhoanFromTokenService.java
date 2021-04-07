package com.example.hotelbooking.WEB_SERVICE.utils;

import com.example.hotelbooking.BUSINESS_SERVICE.service.TaiKhoanService;
import com.example.hotelbooking.DAO.model.TaiKhoan;
import com.example.hotelbooking.WEB_SERVICE.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetTaiKhoanFromTokenService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private TaiKhoanService taiKhoanService;

    public TaiKhoan getTaiKhoan(String token) {
        long id = jwtTokenProvider.getUserIdFromJWT(token);
        return taiKhoanService.findByMaTaiKhoanAndDeletedFalse(id);
    }
}
