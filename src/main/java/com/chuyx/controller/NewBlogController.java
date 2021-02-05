package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.BlogApi;
import com.chuyx.service.BlogService;
import com.chuyx.wrapper.BlogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author chuyx
 * @data 2020-12-20
 */
@Controller
public class NewBlogController implements BlogApi {

    @Autowired
    private BlogService blogService;


    @Override
    public String hotBlog() {
        return JSON.toJSONString(blogService.getHotBlog());
    }

    @Override
    public String newestBlog() {
        return JSON.toJSONString(blogService.getNewestBlog());
    }

    @Override
    public String queryPageBlog(BlogWrapper.QueryPageDTO req) {
        return JSON.toJSONString(blogService.queryPageBlog(req));
    }

    @Override
    public String queryBlogById(BlogWrapper.QueryBlogDTO req) {
        return JSON.toJSONString(blogService.queryBlogById(req.getId()));
    }

    @Override
    public Integer save(BlogWrapper.SaveBlogDTO req) {
        return blogService.save(req);
    }

    @Override
    public Integer softDelete(BlogWrapper.SoftDeleteDTO req) {
        return blogService.softDeleteBlog(req.getId());
    }

    @Override
    public String searchBlogByComment(BlogWrapper.SearchDTO req) {
        return JSON.toJSONString(blogService.searchBlogByComment(req.getComment()));
    }
}
