package com.wyb.spatialquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 *地图查询服务
 *
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpatialQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpatialQueryApplication.class, args);
    }

}
