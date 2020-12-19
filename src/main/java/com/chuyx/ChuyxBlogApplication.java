package com.chuyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
@EnableSwagger2
public class ChuyxBlogApplication {
   public static void main(String[] args) {
      SpringApplication.run(ChuyxBlogApplication.class, args);
   }
}
