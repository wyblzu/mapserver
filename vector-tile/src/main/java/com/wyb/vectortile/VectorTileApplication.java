package com.wyb.vectortile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 *矢量瓦片服务
 *
 * @author wangyongbing
 * @date 2019/10/10 21:40
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class VectorTileApplication {

    public static void main(String[] args) {
        SpringApplication.run(VectorTileApplication.class, args);
    }

}
