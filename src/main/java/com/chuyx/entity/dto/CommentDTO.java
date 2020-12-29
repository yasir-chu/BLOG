package com.chuyx.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable {

   private int uid;

   private String content;

   private int parentId;

   private int blogId;
}
