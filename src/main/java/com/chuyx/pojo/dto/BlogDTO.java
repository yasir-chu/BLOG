package com.chuyx.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {

   private int id;

   private int uid;

   private String author;

   private String year;

   private String month;

   private String day;

   private String content;

   private String title;

   private String smallTitle;

   private int visitCount;

   private String catecoty;

   private int count;
}
