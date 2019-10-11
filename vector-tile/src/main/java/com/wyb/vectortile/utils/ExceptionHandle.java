package com.wyb.vectortile.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-10-10 23:37
 **/
@ControllerAdvice
public class ExceptionHandle {

    /**
     *
     * 全局异常
     *
     * @author wangyongbing
     * @date 2019/10/10 23:41
     *
     * @param businessException 业务异常
     *
     * @return {@link BaseResult}
     *
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public BaseResult errorHandle(BusinessException businessException) {
        return ResultUtil.fail(businessException.getCode(), businessException.getMessage());
    }

    /**
     *
     * 全局异常
     *
     * @author wangyongbing
     * @date 2019/10/10 23:43
     *
     * @param exception 异常
     *
     * @return {@link BaseResult}
     *
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult errorHandler(Exception exception) {
        return ResultUtil.fail(exception.getMessage());
    }


}

