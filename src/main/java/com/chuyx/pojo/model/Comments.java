package com.chuyx.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments implements Serializable {

   private int id;

   private int uid;

   private Date createTime;

   private String content;

   private int parentId;

   private int blogId;

   private String authorOne;

   private String authorTwe;
}

