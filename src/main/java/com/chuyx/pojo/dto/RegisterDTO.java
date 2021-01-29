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
public class RegisterDTO implements Serializable {

   @ApiModelProperty(notes = "用户名")
   public String uname;

   @ApiModelProperty(notes = "电话")
   public String phone;

   @ApiModelProperty(notes = "生日")
   public String brith;

   @ApiModelProperty(notes = "邮箱")
   public String email;

   @ApiModelProperty(notes = "性别")
   public Integer sex;

   @ApiModelProperty(notes = "密码")
   public String password;

   @ApiModelProperty(notes = "二次确认密码")
   public String repeatPassword;

   @ApiModelProperty(notes = "头像路径")
   private String headPic;
}
