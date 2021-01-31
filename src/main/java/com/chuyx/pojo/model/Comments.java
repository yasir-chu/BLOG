package com.chuyx.pojo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Comments")
public class Comments implements Serializable {

   /**
    * 主键id
    */
   @TableId(type = IdType.AUTO)
   private Integer id;

   /**
    * 发表用户id
    */
   @TableField("uid")
   private Integer uid;

   /**
    * 创建时间
    */
   @TableField("create_time")
   private Date createTime;

   /**
    * 评论内容
    */
   @TableField("content")
   private String content;

   /**
    * 父评论id
    */
   @TableField("parent_id")
   private Integer parentId;

   /**
    * 评论博客id
    */
   @TableField("blog_id")
   private Integer blogId;

   /**
    * 父评论的用户名
    */
   @TableField("author_one")
   private String authorOne;

   /**
    * 该评论的用户名
    */
   @TableField("author_twe")
   private String authorTwe;
}

