package com.wyb.vectortile.utils;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:35
 **/
public class BusinessException extends RuntimeException {

    @Getter
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}

