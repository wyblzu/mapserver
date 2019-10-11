package com.wyb.vectortile.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * swagger2配置
 * @author wangyongbing
 * @date 2019/9/8 19:59
 * @version 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    @Autowired
    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().enable(swaggerProperties.isEnabled())
                .apiInfo(apiInfo()).useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Lists.newArrayList(
                        new ResponseMessageBuilder()
                                .code(500)
                                .message("服务器发生异常")
                                .responseModel(new ModelRef("string"))
                                .build(),
                        new ResponseMessageBuilder()
                                .code(403)
                                .message("资源不可用")
                                .build()
                ));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact(swaggerProperties.getAuthor(), "", "");
        return new ApiInfo(swaggerProperties.getTitle(), swaggerProperties.getDescription(), swaggerProperties.getVersion(), "服务列表",
                contact,
                swaggerProperties.getGroupName(), "待定", Collections.emptyList());
    }
}
