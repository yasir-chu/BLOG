package com.chuyx.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO implements Serializable {
   private int uid;

   private String uname;

   private String password;

   private int capacity;

   private String headPic;

}
