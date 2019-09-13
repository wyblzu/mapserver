package com.wyb.spatialquery.utils;

import java.util.HashMap;

/**
 * 自定义响应值返回格式
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-13 10:29
 **/
public class CustomResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public CustomResponse() {
        put("success", true);
        put("message", "successful");
        put("code", 200);
    }

    @Override
    public CustomResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static CustomResponse ok(Object value) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.put("data", value);
        return customResponse;
    }

    public static CustomResponse error(String code, String message) {
        CustomResponse customResponse = new CustomResponse();
        customResponse.put("success", false);
        customResponse.put("message", message);
        customResponse.put("code", code);
        return customResponse;
    }

    public static CustomResponse error() {
        return error("500", "未知异常，请联系管理员");
    }

    public static CustomResponse error(String message) {
        return error("500", message);
    }
}

