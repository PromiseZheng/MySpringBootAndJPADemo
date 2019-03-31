package com.test.demo.Services;

import com.test.demo.Dao.GoodsDao;
import com.test.demo.Entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GoodsService {
    @Autowired
    GoodsDao goodsDao;
    public List<Goods> getAllGoods( Integer page, Integer size){
        if(page==null){
            page=0;
        }
        if(size==null){
            size=10;
        }
        List<Goods> goods=goodsDao.findGoodsByBuyerIdIsNull();
        return goods;
    }
    public List<Goods> getGoodsBySellerId(String id){
        List<Goods> goods = goodsDao.findBySellerid(id);
        return goods;
    }
    public Goods getGoodsById(String id){
        if(goodsDao.findById(id).isPresent()){
            Goods goods=goodsDao.findById(id).get();
            return  goods;
        }else{
            return null;
        }
    }
    public List<Goods> getGoodsByBuyerId(String id){
        List<Goods> goods = goodsDao.findByBuyerId(id);
        return goods;
    }
    public boolean insert(Goods goods){
        try{
            goodsDao.save(goods);
            return true;
        }catch (Exception e){
            return false;
        }

    }
    public boolean deleteById(String id){
        try{
            goodsDao.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
