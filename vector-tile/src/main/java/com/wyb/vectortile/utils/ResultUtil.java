package com.wyb.vectortile.utils;

/**
 * 统一处理响应结果和异常
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:26
 **/
public class ResultUtil {

    public static BaseResult success() {
        return new BaseResult(200, "success", null);
    }

    public static BaseResult success(Object data) {
        return new BaseResult(200, "success", data);
    }

    public static BaseResult success(Integer code, String message) {
        return new BaseResult(code, message, null);
    }

    public static BaseResult fail(String message) {
        return new BaseResult(500, message, null);
    }

    public static BaseResult fail(Integer code, String message) {
        return new BaseResult(code, message, null);
    }


}

