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

public class BlogWrapper {


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "BlogWrapper.QueryPageDTO")
    public static class QueryPageDTO {

        @NotNull
        @ApiModelProperty(notes = "页面大小")
        private Integer size;

        @NotNull
        @ApiModelProperty(notes = "页数")
        private Integer page;

        @ApiModelProperty(notes = "类别id")
        private Integer ordinaryId;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "BlogWrapper.QueryBlogDTO")
    public static class QueryBlogDTO {

        @NotNull
        @ApiModelProperty(notes = "博客id")
        private Integer id;

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("BlogWrapper.SaveBlogDTO")
    public static class SaveBlogDTO {

        @ApiModelProperty(notes = "博客id 不传新增 传了修改")
        private Integer id;

        @NotNull
        @ApiModelProperty(notes = "博客标题")
        private String title;

        @NotNull
        @ApiModelProperty(notes = "博客副标题")
        private String smallTitle;

        @NotNull
        @ApiModelProperty(notes = "博客内容")
        private String content;

        @NotNull
        @ApiModelProperty(notes = "类别id")
        private Integer capacity;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "BlogWrapper.SoftDeleteDTO")
    public static class SoftDeleteDTO {

        @NotNull
        @ApiModelProperty(notes = "博客id")
        private Integer id;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "BlogWrapper.SearchDTO")
    public static class SearchDTO {

        @NotNull
        @ApiModelProperty(notes = "查询内容")
        private String comment;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "UserWrapper.SearchBlogDTO")
    public static class SearchBlogDTO {

        @ApiModelProperty(notes = "发布时间范围")
        private String date;

        @ApiModelProperty(notes = "博客标题")
        private String blogTitle;

    }
}
