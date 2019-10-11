package com.wyb.vectortile.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 统一处理响应结果和异常
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:21
 **/
@Data
@AllArgsConstructor
public class BaseResult<T> {

    private Integer code;

    private String message;

    private T data;

}

