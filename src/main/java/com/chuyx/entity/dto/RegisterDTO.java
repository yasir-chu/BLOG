package com.chuyx.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO implements Serializable {
   private int uid;
   private int capacity;
   public String username;
   public String phone;
   public String brith;
   public String email;
   public int sex;
   public String password;
   public String repeatPassword;
   private String headPic;
}
