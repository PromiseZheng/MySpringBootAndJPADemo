package com.test.demo.Controller.loginController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.test.demo.Services.loginService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.oops.Method;

import java.util.HashMap;

@Controller
public class testController {
    @Autowired
    loginService service;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public String login(Model model, @RequestParam(value="userName") String userName, @RequestParam(value = "password") String password ){
        String p=service.login(userName,password);
        if(p.equals("登录成功")){
            model.addAttribute("userName",userName);
            return "task";
        }else if(p.equals("密码错误")){
            return "password failure";
        }
        return "failure";
    }
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    public String register(@RequestParam(value="userName")String userName,@RequestParam(value="password") String password){
        String p=service.register(userName,password);
        if(p.equals("注册成功")){
            return "1";
        }else if(p.equals("账号已存在")){
            return "2";
        }else return "failure";
    }
}
