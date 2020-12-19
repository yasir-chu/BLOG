package com.chuyx.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    @TableField("主键id")
    private int id;

    @TableField("类别名")
    private String name;
}
