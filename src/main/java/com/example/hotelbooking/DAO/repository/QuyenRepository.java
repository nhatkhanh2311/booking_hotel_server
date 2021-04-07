package com.example.hotelbooking.DAO.repository;

import java.util.ArrayList;

import com.example.hotelbooking.DAO.model.DacQuyen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface QuyenRepository extends CrudRepository<DacQuyen, Long> {
    ArrayList<DacQuyen> findAll();

    DacQuyen findByTenDacQuyen(String tenDacQuyen);
}
