package com.example.hotelbooking.DAO.repository;

import com.example.hotelbooking.DAO.model.GioiTinh;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GioiTinhRepository extends CrudRepository<GioiTinh, Long> {
    GioiTinh findByTenGioiTinh(String tenGioiTinh);
    ArrayList<GioiTinh> findAll();
}
