package com.chuyx.api;

import com.chuyx.wrapper.BlogWrapper;
import com.chuyx.wrapper.UserWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @data 2021-04-17
 */
@Api(tags = "后台api")
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
     * 拒绝用户审批
     * @param uid 用户id
     * @return 等待审批的博主
     */
    @PostMapping(value = "/admin/refuseAuthor/{uid}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("通过审批")
    String refuseAuthor(@PathVariable("uid") Integer uid);

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

    /**
     * 获取分页类别
     * @param page 当前页面
     * @return 类别
     */
    @PostMapping(value = "/admin/queryPageCategory/{page}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("获取分页类别")
    String queryPageCategory(@PathVariable("page") Integer page);

    /**
     * 删除类别
     * @param id 类别id
     * @return 管理页面
     */
    @PostMapping(value = "/admin/delCategory/{id}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("删除类别")
    String delCategory(@PathVariable("id") Integer id);

    /**
     * 保存类别
     * @param id 类别id
     * @param name 类别名
     * @return 管理页面
     */
    @PostMapping(value = "/admin/saveCategory/{id}/{name}",  produces = {"application/json;charset=utf-8"})
    @ApiOperation("保存类别")
    String saveCategory(@PathVariable("id") Integer id, @PathVariable("name") String name);

    /**
     * 查询用户
     * @param searchUserDTO 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/admin/searchUser", produces = {"application/json;charset=utf-8"})
    @ApiOperation("查询用户")
    String searchUser(@RequestBody UserWrapper.SearchUserDTO searchUserDTO);

    /**
     * 查询博客
     * @param searchBlogDTO 查询条件
     * @return 查询结果
     */
    @PostMapping(value = "/admin/searchBlog", produces = {"application/json;charset=utf-8"})
    @ApiOperation("查询用户")
    String searchBlog(@RequestBody BlogWrapper.SearchBlogDTO searchBlogDTO);

}
