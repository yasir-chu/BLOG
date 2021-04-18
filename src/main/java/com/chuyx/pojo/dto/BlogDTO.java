package com.chuyx.pojo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cyx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

   @ApiModelProperty(notes = "博客id")
   private Integer id;

   @ApiModelProperty(notes = "作者id")
   private Integer uid;

   @ApiModelProperty(notes = "作者名")
   private String author;

   @ApiModelProperty(notes = "年")
   private String year;

   @ApiModelProperty(notes = "月")
   private String month;

   @ApiModelProperty(notes = "日")
   private String day;

   @ApiModelProperty(notes = "内容")
   private String content;

   @ApiModelProperty(notes = "标题")
   private String title;

   @ApiModelProperty(notes = "副标题")
   private String smallTitle;

   @ApiModelProperty(notes = "访问量")
   private Integer visitCount;

   @ApiModelProperty(notes = "类别")
   private String catecoty;

   @ApiModelProperty(notes = "评论数")
   private Integer count;
}
