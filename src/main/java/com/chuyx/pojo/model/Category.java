package com.chuyx.pojo.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author cyx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("category")
public class Category implements Serializable {

   /**
    * 主键id
    */
   @TableId
   private Integer id;

   /**
    * 类别名
    */
   @TableField("name")
   private String name;
}
