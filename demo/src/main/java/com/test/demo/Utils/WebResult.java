package com.test.demo.Utils;

import java.util.HashMap;
import java.util.List;

public class WebResult extends HashMap<String,Object> {

    public WebResult() {
        put("code", 200);
    }

    /**
     * @Description 返回成功结果
     * @Author
     */
    public static WebResult ok() {
        return new WebResult();
    }

    public static WebResult ok(String msg) {
        WebResult result = new WebResult();
        result.put("data", msg);
        return result;
    }

    public static WebResult ok(String code, List msg, int count) {
        WebResult result = new WebResult();
        result.put("code", code);
        result.put("data", msg);
        result.put("total",count);
        return result;
    }

    /**
     * @Description 返回错误
     * @Author
     */
    public static WebResult error() {
        return error(500, "未知错误，请稍后重试!");
    }

    public static WebResult error(String msg) {
        return error(500, msg);
    }

    public static WebResult error(int code, String msg) {
        WebResult result = new WebResult();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public WebResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}