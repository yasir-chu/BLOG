package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.CommentApi;
import com.chuyx.service.CommentsService;
import com.chuyx.wrapper.CommentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author yasir.chu
 * @date 2021/1/29
 **/
@Controller
public class NewCommentController implements CommentApi {

    @Autowired
    private CommentsService commentsService;

    @Override
    public String queryPage(int page, int blogId) {
        return JSON.toJSONString(commentsService.queryPage(page, blogId));
    }
}
