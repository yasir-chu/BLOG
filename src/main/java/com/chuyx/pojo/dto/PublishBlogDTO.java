package com.chuyx.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cyx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishBlogDTO implements Serializable {

   @ApiModelProperty(notes = "博客id")
   private Integer id;

   @ApiModelProperty(notes = "博客标题")
   private String title;

   @ApiModelProperty(notes = "博客副标题")
   private String smallTitle;

   @ApiModelProperty(notes = "博客内容")
   private String content;

   @ApiModelProperty(notes = "类别id")
   private Integer capacity;
}
