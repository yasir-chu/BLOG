package com.chuyx.pojo.model;

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
@TableName("user")
public class User implements Serializable {

   /**
    * 主键id
    */
   @TableId
   private Integer uid;

   /**
    * 用户名
    */
   @TableField("uname")
   private String uname;

   /**
    * 密码
    */
   @TableField("password")
   private String password;

   /**
    * 生日
    */
   @TableField("brith")
   private Date brith;

   /**
    * 性别 1 男 0 女
    */
   @TableField("sex")
   private Integer sex;

   /**
    * 邮箱
    */
   @TableField("email")
   private String email;

   /**
    * 电话
    */
   @TableField("phone")
   private String phone;

   /**
    * 级别
    */
   @TableField("capacity")
   private Integer capacity;

   /**
    * 个人签名
    */
   @TableField("logged")
   private String logged;

   /**
    * 头像地址
    */
   @TableField("headPic")
   private String headPic;
}

