package com.wyb.spatialquery.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 自定义异常处理
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-13 18:50
 **/
@RestControllerAdvice
@Slf4j
public class ExtendExceptionHandle {


    @ResponseBody
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "method argument not match";
        log.info(error + ",detail info: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder().withStatus(status)
                .withErrorCode(HttpStatus.BAD_REQUEST.name())
                .withMessage(ex.getLocalizedMessage()).build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ResponseBody
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request ";
        log.info(error + ",detail info: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withErrorCode("BAD_DATA")
                .withMessage(ex.getLocalizedMessage())
                .withDetail(error + ex.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> httpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "request method not support";
        log.info(error + ",detail info: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withErrorCode(HttpStatus.METHOD_NOT_ALLOWED.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(error + ex.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());

    }

    @ResponseBody
    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<Object> httpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "media type not supported";
        log.info(error + ",detail info: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withErrorCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(error + ex.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ResponseBody
    @ExceptionHandler({TypeMismatchException.class})
    public ResponseEntity<Object> typeMismatchException(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "type not match";
        log.info(error + ",detail info: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withErrorCode(HttpStatus.NOT_ACCEPTABLE.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(error + ex.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> defaultException(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "unknown exception";
        log.info(error + ",detail info: {}", ex.getMessage());
        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(status)
                .withErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .withMessage(ex.getLocalizedMessage())
                .withDetail(error + ex.getMessage()).build();
        return new ResponseEntity<>(response, response.getStatus());
    }
}

