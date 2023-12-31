    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ntt.formatter.LoaiBaiVietFormatter;
import com.ntt.formatter.LoaiTaiKhoanFormatter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ThanhThuyen
 */
@Configurable
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ntt.controllers",
    "com.ntt.repository",
    "com.ntt.service"
})
public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

//    @Bean
//    public InternalResourceViewResolver
//            getInternalResourceViewResolver() {
//        InternalResourceViewResolver resolver
//                = new InternalResourceViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/pages/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resource/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resource/img/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");

        return resolver;

    }

    @Bean
    public Cloudinary cloudinary() {

        Cloudinary c = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpp5kyfae",
                "api_key", "515925565185764",
                "api_secret", "htAoHniRW5N34DQkcBo2Jh_5-XM",
                "secure", true));
        
        return c;
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addFormatter(new LoaiTaiKhoanFormatter());
        registry.addFormatter(new LoaiBaiVietFormatter());
    
    }
}
