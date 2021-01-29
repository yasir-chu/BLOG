package com.chuyx.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yasir.chu
 * @date 2021/1/29
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentShowVO {

    @ApiModelProperty(notes = "最大父评论")
    private CommentBaseVO parent;

    @ApiModelProperty(notes = "父评论下的所有子评论")
    private List<CommentBaseVO> children;

}
