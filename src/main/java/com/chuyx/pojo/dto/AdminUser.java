package com.chuyx.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
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
public class AdminUser implements Serializable {

   @ApiModelProperty(notes = "用户id")
   private Integer uid;

   @ApiModelProperty(notes = "用户名")
   private String uname;

   @ApiModelProperty(notes = "级别")
   private Integer capacity;

   @ApiModelProperty(notes = "邮箱")
   private String email;

   @ApiModelProperty(notes = "生日")
   private String brith;


}
