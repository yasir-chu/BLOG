package com.chuyx.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
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
public class Blog implements Serializable {

    @TableField("")
    @ApiModelProperty("主键id")
    private int id;

    @TableField("")
    @ApiModelProperty("用户id")
    private Integer uid;

    @TableField("")
    @ApiModelProperty("发布时间")
    private Date releaseDate;

    @ApiModelProperty("主标题")
    private String title;

    @ApiModelProperty("副标题")
    private String smallTitle;

    @ApiModelProperty("类别id")
    private Integer categoryId;

    @ApiModelProperty("访问数")
    private Integer visitCount;

    @ApiModelProperty("博客内容")
    private String content;
}
