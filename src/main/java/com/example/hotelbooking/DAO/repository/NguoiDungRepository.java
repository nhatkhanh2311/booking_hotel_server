package com.example.hotelbooking.DAO.repository;

import com.example.hotelbooking.DAO.model.NguoiDung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NguoiDungRepository extends PagingAndSortingRepository<NguoiDung, Long> {

    Page<NguoiDung> findAll(Pageable pageable);

}
