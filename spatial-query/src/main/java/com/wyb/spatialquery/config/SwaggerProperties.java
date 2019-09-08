package com.wyb.spatialquery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
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
