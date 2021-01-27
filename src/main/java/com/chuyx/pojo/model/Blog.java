package com.chuyx.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Blog")
public class Blog implements Serializable {

   /**
    * 主键id
    */
   @TableId
   private Integer id;

   /**
    * 作者id
    */
   @TableField("uid")
   private Integer uid;

   /**
    * 发布日期
    */
   @TableField("release_date")
   private Date releaseDate;

   /**
    * 标题
    */
   @TableField("title")
   private String title;

   /**
    * 副标题
    */
   @TableField("small_title")
   private String smallTitle;

   /**
    * 类别id
    */
   @TableField("category_id")
   private Integer categoryId;

   /**
    * 访问量
    */
   @TableField("visit_count")
   private Integer visitCount;

   /**
    * 内容
    */
   @TableField("content")
   private String content;
}
