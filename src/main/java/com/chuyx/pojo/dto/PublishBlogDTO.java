package com.chuyx.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishBlogDTO implements Serializable {

   private int id;

   private String title;

   private String smallTitle;

   private String content;

   private int capacity;
}
