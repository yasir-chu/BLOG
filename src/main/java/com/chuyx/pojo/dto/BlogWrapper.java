package com.chuyx.pojo.dto;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author yasir.chu
 * @date 2021/1/21
 **/

public class BlogWrapper {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "BlogWrapper.QueryPageDTO")
    public static class QueryPageDTO {

        @NotNull
        private Integer size;

        @NotNull
        private Integer page;

    }

}
