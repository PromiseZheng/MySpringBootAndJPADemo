package com.test.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "goods")
public class Goods {
    @Id
    private String goodsid;
    private String goodsName;
    private String price;
    private String detail;
    private String sellerid;
    private String sellDate;
    private String upToStoreDate;
    private String buyerId;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getUpToStoreDate() {
        return upToStoreDate;
    }

    public void setUpToStoreDate(String upToStoreDate) {
        this.upToStoreDate = upToStoreDate;
    }
}
