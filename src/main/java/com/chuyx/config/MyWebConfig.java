package com.chuyx.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.Controller;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author yasir.chu
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
   @Override
   public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/**").allowedOrigins(new String[]{"*"}).allowedMethods(new String[]{"GET", "POST", "OPTIONS"}).allowedHeaders(new String[]{"*"}).allowCredentials(true).maxAge(3600L);
   }

   @Bean
   public Docket docket(){
      return new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(apiInfo())
              // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.chuyx.controller"))
              .build();
   }

   /**
    * 配置文档信息
    *
    * @return 文档信息
    */
   private ApiInfo apiInfo() {
      Contact contact = new Contact("yasir.chu", "null", "cyx_serendipity@126.com");
      return new ApiInfo(
              // 标题
              "chuyx-blog",
              // 描述
              "yasir.chu的毕业设计",
              // 版本
              "v1.0",
              // 组织链接
              "无",
              // 联系人信息
              contact,
              // 许可
              "无",
              // 许可连接
              "无",
              // 扩展
              new ArrayList<>()
      );
   }
}
