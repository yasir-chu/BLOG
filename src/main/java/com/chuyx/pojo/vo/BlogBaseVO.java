package com.chuyx.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yasir.chu
 * @date 2021/1/21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogBaseVO {

    @ApiModelProperty(notes = "博客id")
    private Integer id;

    @ApiModelProperty(notes = "作者id")
    private Integer uid;

    @ApiModelProperty(notes = "作者名")
    private String author;

    @ApiModelProperty(notes = "发布年份")
    private String year;

    @ApiModelProperty(notes = "发布月")
    private String month;

    @ApiModelProperty(notes = "发布日")
    private String day;

    @ApiModelProperty(notes = "博客内容")
    private String content;

    @ApiModelProperty(notes = "博客标题")
    private String title;

    @ApiModelProperty(notes = "博客副标题")
    private String smallTitle;

    @ApiModelProperty(notes = "访问人数")
    private Integer visitCount;

    @ApiModelProperty(notes = "类别id")
    private Integer categoryId;

    @ApiModelProperty(notes = "类别")
    private String category;

    @ApiModelProperty(notes = "评论数量")
    private Integer count;
}
