package com.wyb.vectortile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * swagger2动态属性
 *
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
@Data
@Component
@ConfigurationProperties("swagger2")
public class SwaggerProperties {

    private boolean enabled;

    private String title;

    private String version;

    private String groupName;

    private String author;

    private String description;

    private String basePackage;

}
