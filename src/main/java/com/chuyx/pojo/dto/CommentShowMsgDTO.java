package com.chuyx.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentShowMsgDTO {

   private int id;

   private String author;

   private String repleaseDate;

   private String content;

   private String authorOne;

   private String authorTwe;

   private String oneHeadPic;

   private String tweHeadPic;


}
