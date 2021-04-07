package com.example.hotelbooking.DAO.model;


import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "GIOITINH")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "maGioiTinh")
public class GioiTinh {
    @Id
    @Column(name = "MaGioiTinh", nullable = false)
    @GeneratedValue
    private long maGioiTinh;

    @Column(name = "TenGioiTinh", nullable = false)
    @Nationalized
    private String tenGioiTinh;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "gioiTinh")
    private Set<NguoiDung> danhSachNguoiDung;

    public long getMaGioiTinh() {
        return maGioiTinh;
    }

    public String getTenGioiTinh() {
        return tenGioiTinh;
    }

    public void setDanhSachNguoiDung(Set<NguoiDung> danhSachNguoiDung) {
        this.danhSachNguoiDung = danhSachNguoiDung;
    }

    public void setMaGioiTinh(long maGioiTinh) {
        this.maGioiTinh = maGioiTinh;
    }

    public void setTenGioiTinh(String tenGioiTinh) {
        this.tenGioiTinh = tenGioiTinh;
    }
}
