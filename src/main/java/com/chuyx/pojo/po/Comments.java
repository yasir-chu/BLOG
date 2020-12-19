package com.chuyx.pojo.po;

import io.swagger.annotations.ApiModelProperty;
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
public class Comments implements Serializable {

    @ApiModelProperty("评论id")
    private int id;

    @ApiModelProperty("用户id")
    private int uid;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("父评论")
    private int parentId;

    @ApiModelProperty("博客id")
    private int blogId;

    @ApiModelProperty("父评论作者昵称")
    private String authorOne;

    @ApiModelProperty("当前作者昵称")
    private String authorTwe;
}

