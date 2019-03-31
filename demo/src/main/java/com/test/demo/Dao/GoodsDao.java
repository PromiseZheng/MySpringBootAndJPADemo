package com.test.demo.Dao;

import com.test.demo.Entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface GoodsDao extends JpaRepository<Goods,String> {
    public List<Goods> findGoodsByBuyerIdIsNull();
    public List<Goods> findBySellerid(String id);
    public List<Goods> findByBuyerId(String id);
    public List<Goods> findByBuyerIdNotNull();
}
