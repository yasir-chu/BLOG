package com.chuyx.controller;

import com.chuyx.api.CommentApi;
import com.chuyx.service.CommentsService;
import com.chuyx.wrapper.CommentWrapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yasir.chu
 * @date 2021/1/29
 **/

public class NewCommentController implements CommentApi {

    @Autowired
    private CommentsService commentsService;

    @Override
    public String queryPage(int page, int blogId) {
        CommentWrapper.QueryPageDTO query = new CommentWrapper.QueryPageDTO();
        query.setPage(page);
        query.setBlogId(blogId);
        return null;
    }
}
