package com.chuyx.api;

import com.chuyx.pojo.vo.CommentBaseVO;
import com.chuyx.wrapper.BlogWrapper;
import com.chuyx.wrapper.CommentWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yasir.chu
 * date 2021/1/27
 **/
@Api(tags = "评论模块")
@ResponseBody
public interface CommentApi {

    /**
     * 分页获取博客评论
     *
     * @param page 页面
     * @param blogId 博客id
     * @return 结果
     */
    @PostMapping(value = "/comments/queryPage/{page}/{blogId}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取博客评论")
    String queryPage(@PathVariable("page") int page, @PathVariable("blogId") int blogId);

    /**
     * 新增一个评论
     *
     * @param insertDTO 新增评论信息
     * @param request 请求信息
     * @return 结果
     */
    @ApiOperation("新增评论")
    @PostMapping(value = "/comments/addComment")
    CommentBaseVO saveComment(CommentWrapper.InsertDTO insertDTO, HttpServletRequest request);
}
