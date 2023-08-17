/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ntt.repository.impl;

import com.ntt.pojo.BaiViet;
import com.ntt.pojo.NguoiDung;

import com.ntt.repository.BaiVietRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThanhThuyen
 */
@Repository
@Transactional
public class BaiVietRepositoryImpl implements BaiVietRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<BaiViet> getBaiViet() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From BaiViet");
        return q.getResultList();
    }

    @Override
    public boolean addBaiViet(BaiViet baiviet) {
        Session s = this.factory.getObject().getCurrentSession();
        String msg="";
        try {
            s.save(baiviet);
            return true;
        } catch (HibernateException e) {
            System.err.println(e.getMessage());
        }
        return false;

    }
    
    
            

    @Override
    public List<BaiViet> getBaiViet(String tenBaiViet) {
        Session s= this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder= s.getCriteriaBuilder();
        CriteriaQuery<NguoiDung> query= builder.createQuery(NguoiDung.class);
        Root root=query.from(NguoiDung.class);
        query= query.select(root);
        
        if(!tenBaiViet.isEmpty())
        {
            Predicate p =builder.equal(root.get("tenBaiViet").as(String.class), tenBaiViet.trim());
            query =query.where(p);
        }
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public Object getBaiVietById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        org.hibernate.query.Query q = s.createQuery("FROM BaiViet WHERE id= :i");
        q.setParameter("i", id);
        return q.getSingleResult();
    }

    @Override
    public List<Object> getBaiVietByType(String loaiBViet) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        List<Predicate> predicates = new ArrayList<>();
        Root rPost = q.from(BaiViet.class);
        q.select(rPost);

        Predicate p = b.equal(rPost.get("loaiBaiViet"), Integer.parseInt(loaiBViet));
        predicates.add(p);
        q.where(predicates.toArray(Predicate[]::new));

        org.hibernate.query.Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public Object getBaiVietByIdNgDung(int idNgDung) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
