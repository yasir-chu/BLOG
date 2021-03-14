package com.chuyx.controller;

import com.chuyx.api.ViewJumpApi;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.ViewJumpService;
import com.chuyx.utils.DozerUtil;
import com.chuyx.service.UserService;
import com.chuyx.wrapper.BlogWrapper;
import com.chuyx.wrapper.CommentWrapper;
import com.chuyx.wrapper.UserWrapper;
import com.sun.mail.imap.protocol.ID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

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
        queryPageDTO.setSize(NormalConstant.TOP_SIZE);
        queryPageDTO.setPage(NormalConstant.ONE);
        Pager<BlogBaseVO> result = blogService.queryPageBlog(queryPageDTO);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }

    @Override
    public String blogPage(int categoryId, int page, Model model) {
        BlogWrapper.QueryPageDTO queryPageDTO = new BlogWrapper.QueryPageDTO();
        queryPageDTO.setSize(NormalConstant.TOP_SIZE);
        queryPageDTO.setPage(page);
        if (categoryId > 0){
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
        if (loginUserDTO == null){
            model.addAttribute("errMsg", "用户或密码错误！");
            return "ordinary/signin";
        }
        session.setAttribute("userMsg", loginUserDTO);
        session.getAttribute("beforeSignin");
        if (session.getAttribute("beforeSignin").toString().contains("bye")){
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


}
