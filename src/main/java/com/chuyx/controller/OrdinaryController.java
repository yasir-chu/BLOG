package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.dto.UpdateUserDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.EmailService;
import com.chuyx.service.UserService;
import com.chuyx.service.impl.LoginServiceImpl;
import com.chuyx.utils.BlogUtils;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/ordinary"})
public class OrdinaryController {
    @Autowired
    LoginServiceImpl loginService;
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

    @RequestMapping({"/tosignup"})
    public String toSignup(Model model) {
        return "ordinary/signup";
    }

    @RequestMapping(
            value = {"/hotblog"},
            produces = {"application/json;charset=utf-8"}
    )
    @ResponseBody
    public String hotBlog() {
        List<Blog> hotBlogs = this.blogService.queryHotBlog();
        return JSON.toJSONString(hotBlogs);
    }

    @RequestMapping(value = {"/newblogs"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String newBlog() {
        List<Blog> newblogs = this.blogService.queryNewBlog();
        return JSON.toJSONString(newblogs);
    }

    @RequestMapping({"/read/{id}"})
    public String read(@PathVariable("id") int id, Model model) {
        Blog blog = this.blogService.queryBlogById(id);
        blog.setVisitCount(blog.getVisitCount() + 1);
        this.blogService.updateBlogVisitCount(blog);
        BlogDTO blogDTO = new BlogDTO();
        blogDTO = BlogUtils.BolgDateToYMD(blog, blogDTO);
        model.addAttribute("blog", blogDTO);
        BeanUtils.copyProperties(blog, blogDTO);
        Category category = this.categoryService.getCategoryById(blog.getCategoryId());
        blogDTO.setCatecoty(category.getName());
        int count = this.commentsService.queryCountByBlogId(blog.getId());
        blogDTO.setCount(count);
        User user = this.userService.queryUserById(blog.getUid());
        blogDTO.setAuthor(user.getUname());
        return "ordinary/read";
    }

    @RequestMapping({"/capacity/{id}"})
    public String capacityBlog(@PathVariable("id") int id, Model model) {
        Pager<BlogDTO> result = this.blogService.queryBlogByPageCata(id, 1, 5);
        result.setCataId(id);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }

    @RequestMapping({"/page/{page}"})
    public String queryBypage(@PathVariable("page") int page, Model model) {
        Pager<BlogDTO> result = this.blogService.queryBlogByPage(page, 5);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }

    @RequestMapping({"/page/{cataid}/{page}"})
    public String queryByPageCata(@PathVariable("cataid") int cataid, @PathVariable("page") int page, Model model) {
        Pager<BlogDTO> result = this.blogService.queryBlogByPageCata(cataid, 1, 5);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }

    @RequestMapping({"/comments/add"})
    public String addComments(int blogId, int uid, String editorContent, HttpServletRequest request) {
        this.commentsService.addComment(blogId, uid, editorContent);
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping({"/addChildComment"})
    public String addChildComment(HttpServletRequest request, @Nullable String targetUserName, int userId, String userParentName, String replyContent, int blogId, int parrentComId) {
        if (!"".equals(targetUserName) && targetUserName != null) {
            this.commentsService.addChildComment(targetUserName, userId, userParentName, replyContent, blogId, parrentComId);
        } else {
            this.commentsService.addChildComment(userId, userParentName, replyContent, blogId, parrentComId);
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = {"/page/comments/{page}/{blogId}"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String commentsSmallPage(@PathVariable("page") int page, @PathVariable("blogId") int blogId, HttpServletRequest request) {
        Pager<CommentShowDTO> commentShowDTOPager = this.commentsService.queryByBlogIdSmallPage(blogId, page);
        StringBuffer requestURL = request.getRequestURL();
        return JSON.toJSONString(commentShowDTOPager);
    }

    @RequestMapping(value = {"/serach"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String serachBlog(String name) {
        List<BlogDTO> blogDTOS = this.blogService.queryBlogSearch(name);
        return JSON.toJSONString(blogDTOS);
    }

    @RequestMapping({"/updateUserMsg/{uid}"})
    public String toUpdateUserMsg(@PathVariable("uid") int uid, Model model) {
        User user = this.userService.queryUserById(uid);
        RegisterDTO oldUserMsg = this.userToLoginUser(user);
        model.addAttribute("oldUserMsg", oldUserMsg);
        return "ordinary/updateUserMsg";
    }

    @RequestMapping({"/updateuser"})
    public String updateUser(UpdateUserDTO updateUserDTO, Model model, HttpSession session) {
        if (updateUserDTO.getHeadPic() == null || "".equals(updateUserDTO.getHeadPic())) {
            updateUserDTO.setHeadPic("http://img.chuyx.top/4c48020657bd561a.jpg");
        }

        this.userService.updateUserMsg(updateUserDTO);
        Pager<BlogDTO> result = this.blogService.queryBlogByPage(1, 5);
        model.addAttribute("blogDTOS", result);
        session.removeAttribute("userMsg");
        LoginUserDTO loginUserDTO = new LoginUserDTO();
        loginUserDTO.setHeadPic(updateUserDTO.getHeadPic());
        loginUserDTO.setUname(updateUserDTO.getUsername());
        loginUserDTO.setPassword(updateUserDTO.getPassword());
        loginUserDTO.setUid(updateUserDTO.getUid());
        session.setAttribute("userMsg", loginUserDTO);
        return "ordinary/article";
    }

    @RequestMapping(value = {"/allca"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String getAllCa() {
        List<Category> all = this.loginService.getAllCategory();
        return JSON.toJSONString(all);
    }

    @RequestMapping({"/toapply"})
    public String toapply() {
        return "ordinary/apply";
    }

    @RequestMapping({"/apply"})
    public String apply(HttpSession session, String editorContent, Model model) {
        LoginUserDTO userMsg = (LoginUserDTO) session.getAttribute("userMsg");
        int uid = userMsg.getUid();
        this.userService.applyBlogUpdate(uid);
        String uname = this.userService.queryUserById(uid).getUname();
        this.emailService.sentEmailToMy(editorContent, uid);
        LoginUserDTO loginUserDTO = (LoginUserDTO) session.getAttribute("userMsg");
        loginUserDTO.setCapacity(-1);
        session.removeAttribute("userMsg");
        session.setAttribute("userMsg", loginUserDTO);
        return "ordinary/suc";
    }

    public RegisterDTO userToLoginUser(User user) {
        RegisterDTO oldUserMsg = new RegisterDTO();
        oldUserMsg.setHeadPic(user.getHeadPic());
        oldUserMsg.setUid(user.getUid());
        oldUserMsg.setUsername(user.getUname());
        oldUserMsg.setCapacity(user.getCapacity());
        oldUserMsg.setPhone(user.getPhone());
        oldUserMsg.setSex(user.getSex());
        oldUserMsg.setEmail(user.getEmail());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(user.getBrith());
        oldUserMsg.setBrith(format);
        return oldUserMsg;
    }
}
