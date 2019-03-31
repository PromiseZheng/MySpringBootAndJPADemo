package com.test.demo.Controller.PageController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class welcomController {
    @GetMapping("/welcom")
    public String getWelcomPage(){
        return "welcom";
    }
    @RequestMapping(value = "/authorize",method = RequestMethod.POST)
    @ResponseBody
    public String getAuthirize(@RequestBody Map<String, Object> params){
        String getFromeWeb= params.get("ticket").toString();
        String aid="2077935491";
        String AppSecretKey="0i-zX_JMXxGqD95_ZLTi_Hw**";
        String Ticket=params.get("ticket").toString();
        String RandStr=params.get("randstr").toString();
        String IP="180.97.33.107";
        String postData="aid="+aid+"&AppSecretKey="+AppSecretKey+"&Ticket="+Ticket+"&Randstr="+RandStr+"&UserIP="+IP;
        String result=welcomController.sendGet("https://ssl.captcha.qq.com/ticket/verify",postData);
        System.out.println(result);
        HashMap<String,String>resultmap=JSON.parseObject(result,HashMap.class);
        if(resultmap.get("err_msg").toString().equals("OK"))
            return "1";
        else
            return "0";
    }
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
