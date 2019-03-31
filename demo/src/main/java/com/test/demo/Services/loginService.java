package com.test.demo.Services;

import com.test.demo.Dao.UserDao;
import com.test.demo.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class loginService {
    @Autowired
    UserDao dao;
    public String login(String name,String pwd){
        if(dao.findById(name).isPresent()){
           if(pwd.equals(dao.findById(name).get().getPassword())){
               return "登录成功";
           }else{
               return "密码错误";
           }
        }
        return "登录失败";
    }
    public String register(String name,String pwd){
        if(!dao.findById(name).isPresent()){
            try{
                User user=new User();
                user.setUserid(name);
                System.out.println(pwd);
                user.setPassword(pwd);
                dao.save(user);
                return "注册成功";
            }catch (Exception e){
               System.out.println(e);
               return "注册失败";
            }
        }else{
            return "账号已存在";
        }
    }
    public String isIn(String t){
        User temp=dao.findById(t).get();
        if(temp!=null){
            return "账号已存在";
        }else{
            return "可以使用";
        }
    }
}
