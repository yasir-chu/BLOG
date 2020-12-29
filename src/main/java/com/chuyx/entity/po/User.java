package com.chuyx.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

   private int uid;

   private String uname;

   private String password;

   private Date brith;

   private int sex;

   private String email;

   private String phone;

   private int capacity;

   private String logged;

   private String headPic;
}

