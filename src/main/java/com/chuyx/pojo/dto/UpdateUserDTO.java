package com.chuyx.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO implements Serializable {

   private int uid;

   private String headPic;

   private String username;

   private String phone;

   private String brith;

   private String email;

   private int sex;

   private String password;
}
