package com.chuyx.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog implements Serializable {
   private int id;


   private int uid;

   private Date releaseDate;

   private String title;

   private String smallTitle;

   private int categoryId;

   private int visitCount;

   private String content;
}
