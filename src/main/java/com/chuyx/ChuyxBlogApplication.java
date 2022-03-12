package com.chuyx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author chuyx
 */
@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableSwagger2
@EnableEurekaClient
@MapperScan("com.chuyx.mapper")
public class ChuyxBlogApplication {
   public static void main(String[] args) {
      SpringApplication.run(ChuyxBlogApplication.class, args);
   }
}
