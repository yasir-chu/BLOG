package com.chuyx.wrapper;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/

public class CommentWrapper {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "CommentWrapper.InsertDTO")
    public static class InsertDTO {

        @NotNull
        @ApiModelProperty(notes = "博客id")
        private Integer blogId;

        @NotNull
        @ApiModelProperty(notes = "评论用户id")
        private Integer uid;

        @NotNull
        @ApiModelProperty(notes = "评论内容")
        private String content;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "CommentWrapper.InsertChildDTO")
    public static class InsertChildDTO {

        @NotNull
        @ApiModelProperty(notes = "博客id")
        private Integer blogId;

        @NotNull
        @ApiModelProperty(notes = "评论用户id")
        private Integer uid;

        @NotNull
        @ApiModelProperty(notes = "评论内容")
        private String content;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "CommentWrapper.QueryPageDTO")
    public static class QueryPageDTO {

        @NotNull
        @ApiModelProperty(notes = "博客id")
        private Integer blogId;

        @NotNull
        @ApiModelProperty(notes = "页数")
        private Integer page;

        @NotNull
        @ApiModelProperty(notes = "页面条数")
        private Integer size;
    }
}
