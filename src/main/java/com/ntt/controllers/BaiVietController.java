/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.pojo.BaiViet;
import com.ntt.pojo.BinhLuan;
import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.BinhLuanService;
import com.ntt.service.FollowService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.NguoiDungService;
import com.ntt.service.TaiKhoanService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class BaiVietController {

    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private LoaiBaiVietService loaiBaiViet;
    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private NguoiDungService ngdungService;
    @Autowired
    private BinhLuanService binhluanService;
    @Autowired
    private FollowService followService;

    @GetMapping("/dangbai")
    public String list(Model model, Authentication authen) {
        model.addAttribute("nguoidung", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
        model.addAttribute("baiviet_role", this.loaiBaiViet.getLoaiBaiViet());
        model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));

        model.addAttribute("baiviet", new BaiViet());
     
        return "dangbai";

    }

    @RequestMapping("/thtin_bviet")
    public String bvietThTin(Model model, @RequestParam Map<String, String> params, Authentication authen) {
        String errMsg = "";
        int id = Integer.parseInt(params.get("baivietId"));
        if (authen != null) {

            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
            NguoiDung nd=this.taikhoan.getTaiKhoan(authen.getName()).get(0);
            model.addAttribute("follows",this.followService.getFollowsKhachHang(nd));
            
           
        }
        model.addAttribute("BaiViet", this.baivietService.getBaiVietById(id));
        model.addAttribute("binhluan", new BinhLuan());
        model.addAttribute("binhluans", this.binhluanService.getBinhLuan(id));
        model.addAttribute("follow", new Follow());
        return "thtin_bviet";
    }

    @PostMapping("/thtin_bviet_bl")
    public String addBinhLuan(Model model, @ModelAttribute(value = "binhluan") BinhLuan binhluan, Authentication authen, @RequestParam Map<String, String> params) {
        String errMsg = "";
        String ms = "";
        int id = Integer.parseInt(params.get("baivietId"));
        if (authen.getName() != null) {
            if (this.binhluanService.addBinhLuan(binhluan) == true) {
                return "forward:/thtin_bviet";
            } else {

                ms = "Đã có lỗi xãy ra";
            }
        }   
        return "index";
    }
    @PostMapping("/thtin_bviet_fl")
    public String addFollow(Model model, @ModelAttribute(value="follow") Follow follow,Authentication authen){
        String ms="";
        if(authen.getName()!=null)
        {
            if(this.followService.addFollow(follow)==true)
            {
                            
                return "redirect:/";
            }else{
                ms="Đã có lỗi xãy ra";
            }
        }
        return "index";
    }
//    @PostMapping("/thtin_bviet_fl")
//    public String addFollow(Model model, @ModelAttribute(value="follow") Follow follow,Authentication authen,@RequestParam Map<String, String> params){
//        String ms="";
//        int id = Integer.parseInt(params.get("baivietId"));
//        if(authen.getName()!=null)
//        {
//            if(this.followService.addFollow(follow))
//            {
//                return "forward:/thtin_bviet";
//            }else{
//                ms="Đã có lỗi xãy ra";
//            }
//        }
//        
//        return "index";
//    }

    @PostMapping("/dangbai")
    public String add(Model model, @ModelAttribute(value = "baiviet") BaiViet baiviet) {
        String errMsg = "";
//        if(baiviet.getIdNguoiDung().getIdLoaiTaiKhoan().getId()==2)
//        {
//           if (!baiviet.getFile().isEmpty() && !baiviet.getFile1().isEmpty() && !baiviet.getFile2().isEmpty()) {
//            if (this.baivietService.addBaiViet(baiviet) == true) {
//
//                return "redirect:/";
//            } else {
//                errMsg = "Đã có lỗi xãy ra";
//            }
//        } else {
//            errMsg = "Chua nhap thong tin";
//        }
//        
//        }else{
//            if (this.baivietService.addBaiViet(baiviet) == true) {
//
//                return "redirect:/";
//            } 
//        }
            if (this.baivietService.addBaiViet(baiviet) == true) {

                return "redirect:/";
            } 
//       


        return "baiviet";

    }
}
