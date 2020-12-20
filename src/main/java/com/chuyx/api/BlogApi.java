package com.chuyx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @data 2020-12-20
 */
@Api(tags = "博客模块")
@ResponseBody
public interface BlogApi {

    /**
     * 获取访问量前10的博客
     * @return 访问量前10的博客
     */
    @PostMapping(value = "/blog/getHotBlog",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("获取访问量前10的博客")
    String hotBlog();

    /**
     * 获取最新的10篇博客
     *
     * @return 最新的10篇博客
     */
    @PostMapping(value = "/blog/getNewestBlog",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("获取访最新的10篇的博客")
    String newestBlog();
}
