package com.wyb.spatialquery.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 统一返回错误信息
 *
 * @author wangyongbing
 * @version 1.0.0
 * @date 2019-09-13 18:38
 **/
@Data
public class ApiErrorResponse {

    private HttpStatus status;

    private String code;

    private String message;

    private String detail;


   public static final class ApiErrorResponseBuilder {
       private HttpStatus status;

       private String code;

       private String message;

       private String detail;

       public ApiErrorResponseBuilder() {

       }

       public ApiErrorResponseBuilder withStatus(HttpStatus status) {
           this.status = status;
           return this;
       }
       public ApiErrorResponseBuilder withErrorCode(String code) {
           this.code = code;
           return this;
       }

       public ApiErrorResponseBuilder withMessage(String message) {
           this.message = message;
           return this;
       }

       public ApiErrorResponseBuilder withDetail(String detail) {
           this.detail = detail;
           return this;
       }

       public ApiErrorResponse build() {

           ApiErrorResponse apiErrorResponse = new ApiErrorResponse();

           apiErrorResponse.status = this.status;

           apiErrorResponse.code = this.code;

           apiErrorResponse.message = this.message;

           apiErrorResponse.detail = this.detail;

           return apiErrorResponse;
       }
   }
}

