package com.chuyx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableSwagger2
@MapperScan("com.chuyx.mapper")
public class ChuyxBlogApplication {
   public static void main(String[] args) {
      SpringApplication.run(ChuyxBlogApplication.class, args);
   }
}
