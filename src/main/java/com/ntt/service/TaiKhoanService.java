/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ntt.service;

import com.ntt.pojo.LoaiTaiKhoan;
import com.ntt.pojo.NguoiDung;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author ThanhThuyen
 */
public interface TaiKhoanService extends UserDetailsService {

    boolean addTaiKhoan(NguoiDung nguoidung);

    List<NguoiDung> getTaiKhoan(String username);
    NguoiDung getTaiKhoanId(int id);
    LoaiTaiKhoan getLoaiTaiKhoan(String tenLoaiTaiKhoan);

}
