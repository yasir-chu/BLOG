package com.chuyx.api;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.PublishBlogDTO;
import com.chuyx.wrapper.CommentWrapper;
import com.chuyx.wrapper.UserWrapper;
import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 错误跳转
     * @return 404页面
     */
    @GetMapping({"/error/404"})
    String error404();

    /**
     * 错误跳转
     * @return 500页面
     */
    @GetMapping({"/error/500"})
    String error500();

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
     * 登录
     * @param loginUser 登录信息
     * @param session session
     * @param model model
     * @return 结果
     */
    @RequestMapping(value = "/views/signIn")
    String signIn(LoginUserDTO loginUser, HttpSession session, Model model);

    /**
     * 去注册页
     *
     * @return 去注册页
     */
    @RequestMapping(value = "/views/signUp")
    String signUp();

    /**
     * 去修改资料页
     * @param uid 用户id
     * @param model model
     * @return  用户信息修改页
     */
    @RequestMapping(value = "/views/updateUser/{uid}")
    String updateUser(@PathVariable("uid") Integer uid, Model model);

    /**
     * 新增最大父评论
     *
     * @param insertDTO 父评论信息
     * @param request 请求数据
     * @return 去注册页
     */
    @RequestMapping(value = "/views/saveComment")
    String saveComment(CommentWrapper.InsertDTO insertDTO, HttpServletRequest request);

    /**
     * 保存用户
     *
     * @param saveDTO 用户信息信息
     * @param session session
     * @param model 模型
     * @return 博客首页
     */
    @RequestMapping({"/views/register"})
    String register(UserWrapper.SaveDTO saveDTO, HttpSession session, Model model);

    /**
     * 后台管理页
     *
     * @param model 模型
     * @return 后台管理首页
     */
    @RequestMapping({"/views/admin"})
    String toAdmin(Model model);

    /**
     * 去审核博主页面
     * @return 审核博主页面
     */
    @RequestMapping({"/views/toPassAuthor"})
    String toPassAuthor();

    /**
     * 去博客管理页面
     * @return 客管理页面
     */
    @RequestMapping({"/views/toBlogAdmin"})
    String toBlogAdmin();

    /**
     * 去评论管理页面
     * @return 评论管理页面
     */
    @RequestMapping({"/views/toCommentsAdmin"})
    String toCommentsAdmin();

    /**
     * 去用户管理页面
     * @return 用户管理页面
     */
    @RequestMapping({"/views/toUserAdmin"})
    String toUserAdmin();

    /**
     * 去用户管理博客页面
     * @param model 模型
     * @param session session
     * @param page 当前页
     * @return 用户管理博客页面
     */
    @RequestMapping({"/views/toUserBlogMange/{page}"})
    String toUserBlogManger(Model model, HttpSession session, @PathVariable("page")Integer page);

    /**
     * 去发布博客页面
     * @return 发布博客页面
     */
    @RequestMapping({"/views/toPublishBlog"})
    String toPublishBlog();

    /**
     * 去发布修改页面
     * @param model 模型
     * @param id 博客id
     * @return 发布修改页面
     */
    @RequestMapping({"/views/toUpdateBlog/{id}"})
    String toUpdateBlog(Model model, @PathVariable("id") Integer id);

    /**
     * 发布博客
     * @param publishBlogDTO 发布博客对象信息
     * @param session session
     * @param model 模型
     * @return 管理页面
     */
    @RequestMapping({"/views/publishBlog"})
    String publishBlog(PublishBlogDTO publishBlogDTO, HttpSession session, Model model);

    /**
     * 删除用户博客
     * @param blogId 博客id
     * @param session session
     * @param model 模型
     * @return 管理页面
     */
    @RequestMapping({"/views/delBlogUser/{blogId}"})
    String publishBlog(HttpSession session, Model model, @PathVariable("blogId") Integer blogId);

    /**
     * 修改博客
     * @param blogId 博客id
     * @param session session
     * @param model 模型
     * @param publishBlogDTO 发表信息
     * @return 管理页面
     */
    @RequestMapping({"/views/updateBlog/{blogId}"})
    String updateBlog(HttpSession session, Model model, PublishBlogDTO publishBlogDTO,  @PathVariable("blogId") Integer blogId);

    /**
     * 去申请页面
     * @return 申请页面
     */
    @RequestMapping({"/views/toApply"})
    String toApply();

    /**
     * 申请博主
     * @param session session
     * @param editorContent 申请文本
     * @return 成功页面
     */
    @RequestMapping({"/views/apply"})
    String apply(HttpSession session, String editorContent);

}
