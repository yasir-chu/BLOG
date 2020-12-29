package com.chuyx.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewBlogDTO {

   private int id;

   private int uid;

   private String author;

   private String content;

   private Date repleseaDate;

   private String title;

   private String smallTitle;

   private int catecoty;
}
