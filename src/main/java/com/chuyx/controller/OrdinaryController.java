package com.chuyx.controller;

import com.chuyx.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author cyx
 */
@Controller
@RequestMapping({"/ordinary"})
public class OrdinaryController {

    // todo 未重构完

    @Autowired
    UserService userService;
    @Autowired
    BlogService blogService;
    @Autowired
    CommentsService commentsService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    EmailService emailService;

    @RequestMapping({"/toLogin"})
    public String login(HttpSession session, HttpServletRequest request) {
        session.setAttribute("beforeSignin", request.getHeader("Referer"));
        return "ordinary/signin";
    }

    @RequestMapping({"/addChildComment"})
    public String addChildComment(HttpServletRequest request,int userId, String userParentName, String replyContent, int blogId, int parentComId) {
        commentsService.addChildComment(userId, userParentName, replyContent, blogId, parentComId);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
