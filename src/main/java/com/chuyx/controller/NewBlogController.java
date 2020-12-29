package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.BlogApi;
import com.chuyx.service.BlogService;
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
}
