package com.chuyx.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chuyx.api.ViewJumpApi;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.*;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.*;
import com.chuyx.utils.DozerUtil;
import com.chuyx.wrapper.BlogWrapper;
import com.chuyx.wrapper.CommentWrapper;
import com.chuyx.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Controller
@Slf4j
public class ViewJumpController implements ViewJumpApi {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private ViewJumpService viewJumpService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private AdminService adminService;

    @Override
    public String error404() {
        return "error/404";
    }

    @Override
    public String error500() {
        return "error/404";
    }

    @Override
    public String index() {
        return "index";
    }

    @Override
    public String blogPage(Model model) {
        BlogWrapper.QueryPageDTO queryPageDTO = new BlogWrapper.QueryPageDTO();
        queryPageDTO.setSize(NormalConstant.PAGE_SIZE);
        queryPageDTO.setPage(NormalConstant.ONE);
        Pager<BlogBaseVO> result = blogService.queryPageBlog(queryPageDTO);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }

    @Override
    public String blogPage(int categoryId, int page, Model model) {
        BlogWrapper.QueryPageDTO queryPageDTO = new BlogWrapper.QueryPageDTO();
        queryPageDTO.setSize(NormalConstant.PAGE_SIZE);
        queryPageDTO.setPage(page);
        if (categoryId > 0) {
            queryPageDTO.setOrdinaryId(categoryId);
        }
        Pager<BlogBaseVO> result = blogService.queryPageBlog(queryPageDTO);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }

    @Override
    public String readBlog(int id, Model model) {
        BlogBaseVO result = viewJumpService.readBlog(id);
        model.addAttribute("blog", result);
        return "ordinary/read";
    }

    @Override
    public String signOut(HttpSession session, Model model) {
        session.removeAttribute("userMsg");
        return blogPage(model);
    }


    @Override
    public String login(HttpSession session, HttpServletRequest request) {
        session.setAttribute("beforeSignin", request.getHeader("Referer"));
        return "ordinary/signin";
    }

    @Override
    public String signIn(LoginUserDTO loginUser, HttpSession session, Model model) {
        LoginUserDTO loginUserDTO = userService.signIn(loginUser);
        if (loginUserDTO == null) {
            model.addAttribute("errMsg", "用户或密码错误！");
            return "ordinary/signin";
        }
        session.setAttribute("userMsg", loginUserDTO);
        session.getAttribute("beforeSignin");
        if (session.getAttribute("blogDTOS") == null) {
            Pager<BlogBaseVO> result = blogService.queryPageBlog(new BlogWrapper.QueryPageDTO(NormalConstant.PAGE_SIZE, NormalConstant.ONE, null));
            model.addAttribute("blogDTOS", result);
        }
        if (session.getAttribute("beforeSignin").toString().contains("bye")) {
            return "ordinary/article";
        }
        return "redirect:" + session.getAttribute("beforeSignin");
    }

    @Override
    public String signUp() {
        return "ordinary/signup";
    }

    @Override
    public String updateUser(Integer uid, Model model) {
        UserWrapper.SaveDTO user = userService.querySaveUserById(uid);
        model.addAttribute("oldUserMsg", user);
        return "ordinary/updateUserMsg";
    }

    @Override
    public String saveComment(CommentWrapper.InsertDTO insertDTO, HttpServletRequest request) {
        commentsService.saveComment(insertDTO);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @Override
    public String register(UserWrapper.SaveDTO saveDTO, HttpSession session, Model model) {
        User user = userService.saveUser(saveDTO);
        LoginUserDTO result = DozerUtil.map(user, LoginUserDTO.class);
        session.setAttribute("userMsg", result);
        return blogPage(model);
    }

    @Override
    public String toAdmin(Model model) {
        AdminIndexMsgDTO result = adminService.toAdmin();
        model.addAttribute("adminMsg", result);
        return "admin/admin";
    }

    @Override
    public String toPassAuthor() {
        return "admin/checkAdmin";
    }

    @Override
    public String toBlogAdmin() {
        return "admin/blogAdmin";
    }

    @Override
    public String toCommentsAdmin() {
        return "admin/commentAdmin";
    }

    @Override
    public String toUserAdmin() {
        return "admin/userAdmin";
    }

    @Override
    public String toUserBlogManger(Model model, HttpSession session, Integer page) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        if (userMsg == null) {
            return "error/404";
        }
        int uid = userMsg.getUid();
        Pager<BlogDTO> result = blogService.queryBlogByPageAuthor(uid, page);
        model.addAttribute("userBlogs", result);
        return "author/blogManger";
    }

    @Override
    public String toPublishBlog() {
        return "author/publishBlog";
    }

    @Override
    public String toUpdateBlog(Model model, Integer id) {
        model.addAttribute("updateBlog", blogService.queryBlogById(id));
        return "author/updateBlog";
    }

    @Override
    public String publishBlog(PublishBlogDTO publishBlogDTO, HttpSession session, Model model) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        blogService.save(DozerUtil.map(publishBlogDTO, BlogWrapper.SaveBlogDTO.class), userMsg.getUid());
        return toUserBlogManger(model, session, NormalConstant.ONE);
    }

    @Override
    public String publishBlog(HttpSession session, Model model, Integer blogId) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        if (userMsg == null) {
            return "error/404";
        }
        int uid = userMsg.getUid();
        blogService.deleteBlog(blogId, uid);
        Pager<BlogDTO> blogDTOPager = blogService.queryBlogByPageAuthor(uid, NormalConstant.ONE);
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }

    @Override
    public String updateBlog(HttpSession session, Model model, PublishBlogDTO publishBlogDTO, Integer blogId) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        if (userMsg == null) {
            return "error/404";
        }
        int uid = userMsg.getUid();
        if (blogId != null && blogId > 0) {
            publishBlogDTO.setId(blogId);
        }
        blogService.save(DozerUtil.map(publishBlogDTO, BlogWrapper.SaveBlogDTO.class), uid);
        Pager<BlogDTO> blogDTOPager = blogService.queryBlogByPageAuthor(uid, NormalConstant.ONE);
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }

    @Override
    public String toApply() {
        return "ordinary/apply";
    }

    @Override
    public String apply(HttpSession session, String editorContent) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        if (userMsg == null){
            return "error/404";
        }
        int uid = userMsg.getUid();
        userService.applyBlogUpdate(uid, editorContent);
        LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("userMsg");
        loginUserDTO.setCapacity(-1);
        session.removeAttribute("userMsg");
        session.setAttribute("userMsg", loginUserDTO);
        return "ordinary/suc";
    }

    @Override
    public String toCategoryAdmin() {
        return "admin/categoryAdmin";
    }


}
