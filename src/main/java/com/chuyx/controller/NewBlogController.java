package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.BlogApi;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.service.BlogService;
import com.chuyx.wrapper.BlogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

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
    public Integer save(BlogWrapper.SaveBlogDTO req, HttpSession session) {
        LoginUserDTO userMsg = (LoginUserDTO)session.getAttribute("userMsg");
        if (userMsg == null){
            return NormalConstant.DOWN_ONE;
        }
        int uid = userMsg.getUid();
        return blogService.save(req, uid);
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
