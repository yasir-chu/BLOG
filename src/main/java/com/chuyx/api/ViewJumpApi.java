package com.chuyx.api;

import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Api(tags = "页面跳转模块")
public interface ViewJumpApi {

    /**
     * 去首页
     *
     * @return 首页
     */
    @RequestMapping(value = {"/", "/index"})
    String index();

    /**
     * 去博客首页
     *
     * @param model 存数据的模型
     * @return 首页博客
     */
    @GetMapping(value = "/views/blogPage")
    String blogPage(Model model);

    /**
     * 博客分页
     *
     * @param model 存数据的模型
     * @param categoryId 类别id 为0 查所有
     * @param page 第几页
     * @return 查询结果信息
     */
    @GetMapping(value = "/views/blogPage/{categoryId}/{page}")
    String blogPage(@PathVariable("categoryId") int categoryId, @PathVariable("page") int page, Model model);

    /**
     * 读博客
     *
     * @param model 存数据的模型
     * @param id 博客id
     * @return 查询结果信息
     */
    @GetMapping(value = "/views/blog/read/{id}")
    String readBlog(@PathVariable("id") int id, Model model);

    /**
     * 注销用户
     *
     * @param session session
     * @param model 存数据模板
     * @return 博客首页
     */
    @GetMapping(value = "/views/signOut/bye")
    String signOut(HttpSession session, Model model);


    /**
     * 去登录页
     *
     * @param session session
     * @param request 请求数据
     * @return 去登录页
     */
    @RequestMapping(value = "/views/login")
    String login(HttpSession session, HttpServletRequest request);

    /**
     * 去注册页
     *
     * @return 去注册页
     */
    @RequestMapping(value = "/views/signUp")
    String signUp();
}
