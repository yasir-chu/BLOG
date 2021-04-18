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
public class LoginUserDTO implements Serializable {

   @ApiModelProperty(notes = "用户id")
   private Integer uid;

   @ApiModelProperty(notes = "用户名")
   private String uname;

   @ApiModelProperty(notes = "申请宣言")
   private String logged;

   @ApiModelProperty(notes = "用户密码 加密后")
   private String password;

   @ApiModelProperty(notes = "用户类型 -1 申请博主中的用户 0普通用户 1博主  2超级管理员")
   private Integer capacity;

   @ApiModelProperty(notes = "头像地址")
   private String headPic;

}
