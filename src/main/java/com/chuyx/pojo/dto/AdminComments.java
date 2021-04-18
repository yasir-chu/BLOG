package com.chuyx.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminComments implements Serializable {

   @ApiModelProperty(notes = "评论id")
   private Integer id;

   @ApiModelProperty(notes = "博客名称")
   private String blogName;

   @ApiModelProperty(notes = "评论内容")
   private String content;

   @ApiModelProperty(notes = "评论时间")
   private String time;

   @ApiModelProperty(notes = "评论用户")
   private String author;

}
