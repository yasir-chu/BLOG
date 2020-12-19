package com.chuyx.controller;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.PublishBlogDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/author"})
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping({"/toPublishBlog"})
    public String toPublishBlog() {
        return "author/publishBlog";
    }

    @RequestMapping({"/publishBlog"})
    public String publishBlog(PublishBlogDTO publishBlogDTO, HttpSession session, Model model) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        this.blogService.addBlog(publishBlogDTO, userMsg.getUid());
        Pager<BlogDTO> blogDTOPager = this.blogService.queryBlogByPageAuthord(userMsg.getUid());
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }

    @RequestMapping({"/blogManger"})
    public String blogManger(HttpSession session, Model model) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        int uid = userMsg.getUid();
        Pager<BlogDTO> blogDTOPager = this.blogService.queryBlogByPageAuthord(uid);
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }

    @RequestMapping({"/blogManger/page/{page}"})
    public String blogManger(@PathVariable("page") int page, HttpSession session, Model model) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        int uid = userMsg.getUid();
        Pager<BlogDTO> blogDTOPager = this.blogService.queryBlogByPageAuthordPage(uid, page);
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }

    @RequestMapping({"/userblog/del/{id}"})
    public String blogMangerDelBlog(@PathVariable("id") int id, HttpServletRequest request, HttpSession session, Model model) {
        this.blogService.deleteBlog(id);
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        int uid = userMsg.getUid();
        Pager<BlogDTO> blogDTOPager = this.blogService.queryBlogByPageAuthord(uid);
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }

    @RequestMapping({"/userBlog/toupdate/{id}"})
    public String blogMangerUpdateBlog(@PathVariable("id") int id, Model model) {
        Blog blog = this.blogService.queryBlogById(id);
        model.addAttribute("updateBlog", blog);
        return "author/updateBlog";
    }

    @RequestMapping({"/userBlog/update/{id}"})
    public String blogMangerUpdateBlogUp(@PathVariable("id") int id, Model model, PublishBlogDTO publishBlogDTO, HttpSession session) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        int uid = userMsg.getUid();
        this.blogService.updateBlog(publishBlogDTO, uid);
        Pager<BlogDTO> blogDTOPager = this.blogService.queryBlogByPageAuthord(uid);
        model.addAttribute("userBlogs", blogDTOPager);
        return "author/blogManger";
    }
}
