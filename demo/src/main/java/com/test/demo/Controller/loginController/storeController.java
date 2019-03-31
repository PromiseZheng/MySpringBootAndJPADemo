package com.test.demo.Controller.loginController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class storeController {
    @GetMapping(value = "/store")
    public String getStore(Model model, @RequestParam(value = "name")String name, HttpSession httpSession){
        model.addAttribute("name",name);
        httpSession.setAttribute("name",name);
        return "store";
    }
    @GetMapping(value = "/logout")
    public String logout(Model model,HttpSession httpSession){
        httpSession.removeAttribute("name");
        return "index";
    }
}
