package com.chuyx.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 评论基本展示数据对象
 *
 * @author yasir.chu
 * @date 2021/1/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentBaseVO {

    @ApiModelProperty(notes = "主键id")
    private Integer id;

    @ApiModelProperty(notes = "发表评论用户名")
    private String author;

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(notes = "发布日期")
    private String releaseDate;

    @ApiModelProperty(notes = "评论内容")
    private String content;

    @ApiModelProperty(notes = "父评论用户名")
    private String authorOne;

    @ApiModelProperty(notes = "该评论用户名")
    private String authorTwe;

    @ApiModelProperty(notes = "该评论用户头像")
    private String oneHeadPic;

    @ApiModelProperty(notes = "父评论用户头像")
    private String tweHeadPic;
}
