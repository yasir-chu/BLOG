package com.chuyx.api;

import com.chuyx.pojo.dto.BlogWrapper;
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

    /**
     * 分页获取博客
     * @param req 分页信息
     * @return 查询结果
     */
    @PostMapping(value = "/blog/getNewestBlog",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取博客")
    String queryPageBlog(BlogWrapper.QueryPageDTO req);

    /**
     * 获取一篇博客
     * @param id 博客id
     * @return 最新的10篇博客
     */
    @PostMapping(value = "/blog/queryBlogById",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("获取一篇博客")
    String queryBlogById(Integer id);
}
