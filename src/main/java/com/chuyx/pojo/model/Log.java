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
@TableName("log")
public class Log implements Serializable {

   /**
    * 主键id
    */
   @TableId(type = IdType.AUTO)
   private Integer id;

   /**
    * 关系用户
    */
   @TableField("uid")
   private Integer uid;

   /**
    * 事件
    */
   @TableField("event")
   private String event;

   /**
    * 日志内容
    */
   @TableField("connent")
   private String connent;

   /**
    * 创建时间
    */
   @TableField("create_time")
   private Date createTime;
}
