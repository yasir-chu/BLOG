package com.chuyx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @data 2021-04-17
 */
@Api(tags = "博客模块")
@ResponseBody
public interface AdminApi {

    /**
     * 分页获取等待审批的博主
     * @param page 当前页
     * @return 等待审批的博主
     */
    @PostMapping(value = "/admin/queryPageWaitPassAuthor/{page}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取等待审批的博主")
    String queryPageWaitPassAuthor(@PathVariable("page") Integer page);

    /**
     * 通过审批
     * @param uid 用户id
     * @return 等待审批的博主
     */
    @PostMapping(value = "/admin/passAuthor/{uid}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("通过审批")
    String passAuthor(@PathVariable("uid") Integer uid);

    /**
     * 分页获取评论
     * @param page 当前页
     * @return 评论
     */
    @PostMapping(value = "/admin/queryPageComments/{page}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取评论")
    String queryPageComments(@PathVariable("page") Integer page);

    /**
     * 删除评论
     * @param id 评论id
     * @return 评论
     */
    @PostMapping(value = "/admin/delComments/{id}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取评论")
    String delComments(@PathVariable("id") Integer id);

    /**
     * 分页获取博客
     * @param page 当前页
     * @return 博客
     */
    @PostMapping(value = "/admin/queryPageBlog/{page}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取博客")
    String queryPageBlog(@PathVariable("page") Integer page);

    /**
     * 删除博客
     * @param id 博客id
     * @return 管理页面
     */
    @PostMapping(value = "/admin/delBlog/{id}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("删除博客")
    String delBlog(@PathVariable("id") Integer id);

    /**
     * 分页获取用户
     * @param page 当前页
     * @return 用户
     */
    @PostMapping(value = "/admin/queryPageUser/{page}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("分页获取博客")
    String queryPageUser(@PathVariable("page") Integer page);

    /**
     * 删除用户
     * @param id 用户id
     * @return 删除数量
     */
    @PostMapping(value = "/admin/delUser/{uid}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("删除博客")
    String delUser(@PathVariable("uid") Integer id);

}
