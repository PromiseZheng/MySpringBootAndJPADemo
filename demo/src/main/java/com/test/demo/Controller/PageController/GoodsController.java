package com.test.demo.Controller.PageController;
import com.test.demo.Services.GoodsService;
import com.test.demo.Utils.WebResult;
import com.test.demo.Entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @GetMapping("/buyGoods")
    public String getGoods(){
        return  "buyGoods";
    }
    @RequestMapping(value = "/getAllGoodsData",method = RequestMethod.GET)
    @ResponseBody
    public WebResult getAllGoodsData(HttpSession session, @RequestParam(name="name")String name){
        try{
            List<Goods> allGoods=goodsService.getAllGoods(null,null);
            return  WebResult.ok("0",allGoods,allGoods.size());
        }catch (Exception e){
            return WebResult.error("500");
        }
    }
    @GetMapping("myGoods")
    public String myGoods(){
        return "mygoods";
    }
    @RequestMapping(value = "/buygoods",method=RequestMethod.POST)
    @ResponseBody
    public String acceptTask(HttpSession session,@RequestBody HashMap<String,Object>map){
        String id = map.get("goodsid").toString();
        String name = map.get("acceptName").toString();

        try{
            Goods goods= goodsService.getGoodsById(id);
            if(goods.getSellerid().equals(name)){
                return "2";
            }
            goods.setBuyerId(name);
            goods.setSellDate(TaskController.getNowDate());
            goodsService.insert(goods);
            return "1";
        }catch (Exception e){
            return "0";
        }

    }
    @RequestMapping("/getAllMyUpGoods")
    @ResponseBody
    public WebResult getAllMyUpGoods(HttpSession session, @RequestParam(name="name")String name){
        try{
            List<Goods> allTask=goodsService.getGoodsBySellerId(name);
            return  WebResult.ok("0",allTask,allTask.size());
        }catch (Exception e){
            return WebResult.error("500");
        }
    }
    @RequestMapping("/getAllMyBuyGoods")
    @ResponseBody
    public WebResult getAllMyBuyGoods(HttpSession session, @RequestParam(name="name")String name){
        try{
            List<Goods> allTask=goodsService.getGoodsByBuyerId(name);
            return  WebResult.ok("0",allTask,allTask.size());
        }catch (Exception e){
            return WebResult.error("500");
        }
    }
    @GetMapping("/goods")
    public String getGoods(HttpSession httpSession){
        return "goods";
    }
    @RequestMapping("/setGoods")
    @ResponseBody
    public String setGoods(HttpSession httpSession, @RequestBody HashMap<String,Object> map){
        Goods goods=new Goods();
        String id= UUID.randomUUID().toString();
        goods.setGoodsid(id);
        if(map.get("goodsname")!=null){
            goods.setGoodsName(map.get("goodsname").toString());
        }
        if(map.get("price")!=null){
            goods.setPrice(map.get("price").toString());
        }
        if(map.get("detail")!=null){
            goods.setDetail(map.get("detail").toString());
        }
        goods.setSellerid(map.get("sellerid").toString());
        goods.setUpToStoreDate(TaskController.getNowDate());
        boolean isInsert=goodsService.insert(goods);
        if(isInsert){
            return "1";
        }else
            return "0";
    }
    @RequestMapping(value = "deleteGoods",method = RequestMethod.POST)
    @ResponseBody
    public String deleteTask(@RequestBody HashMap<String,Object>map){
        String id=map.get("goodsid").toString();
        Goods goods=goodsService.getGoodsById(id);
        if(goods.getBuyerId()!=null){
            return "2";
        }
        try{
            goodsService.deleteById(id);
            return "1";
        }catch (Exception e){
            return "0";
        }

    }}
