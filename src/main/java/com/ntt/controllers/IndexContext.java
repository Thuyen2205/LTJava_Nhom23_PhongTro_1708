/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.controllers;

import com.ntt.pojo.Follow;
import com.ntt.pojo.NguoiDung;
import com.ntt.service.BaiVietService;
import com.ntt.service.FollowService;
import com.ntt.service.LoaiBaiVietService;
import com.ntt.service.TaiKhoanService;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThanhThuyen
 */
@Controller
@Transactional
public class IndexContext {

    @Autowired
    private BaiVietService baiviet;
    @Autowired
    private TaiKhoanService taikhoan;
    @Autowired
    private BaiVietService baivietService;
    @Autowired
    private LoaiBaiVietService loaiBaiViet;
    @Autowired
    private FollowService followService;
    @RequestMapping("/")
    public String index(Model model, NguoiDung nguoidung, Authentication authen,@RequestParam Map<String, String> params) {
        String errMsg="";
        if (authen != null) {
            model.addAttribute("baiviet", this.baivietService.getBaiViet().get(0));
            model.addAttribute("taikhoan", this.taikhoan.getTaiKhoan(authen.getName()).get(0));
//            NguoiDung nd=this.taikhoan.getTaiKhoan(authen.getName()).get(0);
//            model.addAttribute("follows",this.followService.getFollowsKhachHang(nd.getId()));
//           
        }
        
        model.addAttribute("baiviet", this.baivietService.getBaiViet());
        model.addAttribute("baiviet_1", this.baivietService.getBaiVietByType("1"));
        model.addAttribute("baiviet_2", this.baivietService.getBaiVietByType("2"));
       
        model.addAttribute("follow",new Follow());
        
        return "index";
    }
//    @PostMapping("/")
//    public String addFollow(Model model, @ModelAttribute(value="follow") Follow follow,Authentication authen){
//        String ms="";
//        if(authen.getName()!=null)
//        {
//            if(this.followService.addFollow(follow)==true)
//            {
//                            
//                return "redirect:/";
//            }else{
//                ms="Đã có lỗi xãy ra";
//            }
//        }
//        return "index";
//    }
}
