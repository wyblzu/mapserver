package com.wyb.vectortile.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域配置
 * @author wangyongbing
 * @date 2019/10/12 15:27
 * @version 1.0.0
 */
@Configuration
public class CrossOriginConfig implements WebMvcConfigurer {


//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                //是否发送Cookie
//                .allowCredentials(true)
//                //放行哪些原始域
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*");
//    }
}
