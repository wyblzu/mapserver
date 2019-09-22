package com.wyb.mapsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * 接口token验证
 *
 * @author wangyongbing
 * @date 2019/9/17 22:57
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MapSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapSecurityApplication.class, args);
    }

}
