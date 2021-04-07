package com.example.hotelbooking.DAO.repository;
import com.example.hotelbooking.DAO.model.TaiKhoan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, Long> {
    TaiKhoan findByTenDangNhap(String tenDangNhap);

    TaiKhoan findByMaTaiKhoan(Long maTaiKhoan);

    TaiKhoan findTopByOrderByMaTaiKhoanDesc();

    TaiKhoan findByTenDangNhapAndMatKhauAndDeletedFalse(String tenDangNhap, String matKhau);

    boolean existsByTenDangNhap(String tenDangNhap);

    boolean existsByMaTaiKhoanAndDeletedFalse(long maTaiKhoan);

    boolean existsByMaTaiKhoanAndMatKhauAndDeletedFalse(long id, String matKhau);
}
