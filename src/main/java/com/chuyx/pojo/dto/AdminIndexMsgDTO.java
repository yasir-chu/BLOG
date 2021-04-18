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
public class AdminIndexMsgDTO implements Serializable {

   @ApiModelProperty(notes = "用户总数")
   private Integer userSize;

   @ApiModelProperty(notes = "博主总数")
   private Integer authorSize;

   @ApiModelProperty(notes = "博客总数")
   private Integer blogSize;

   @ApiModelProperty(notes = "评论总数")
   private Integer commentsSize;
}
