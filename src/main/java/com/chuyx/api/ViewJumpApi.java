package com.chuyx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
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
    @RequestMapping(value = "/views/blogPage")
    String blogPage(Model model);

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

    /**
     * 读取一篇博客
     *
     * @param id 博客id
     * @param model 模型 存储博客信息
     * @return 博客信息
     */
    @RequestMapping(value = "/views/blog/read/{id}")
    String readBlog(@PathVariable("id") int id, Model model);
}
