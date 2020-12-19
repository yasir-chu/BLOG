package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.LoginService;
import com.chuyx.service.UserService;
import com.chuyx.utils.BlogUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
   @Autowired
   LoginService loginService;
   @Autowired
   BlogService blogService;
   @Autowired
   CategoryService categoryService;
   @Autowired
   CommentsService commentsService;
   @Autowired
   UserService userService;

   @RequestMapping({"/", "/index"})
   public String index() {
      return "index";
   }

   @RequestMapping({"/loginCheck"})
   public String loginCheck(LoginUserDTO loginUser, HttpSession session, Model model) {
      LoginUserDTO loginUserDTO = this.loginService.queryUserByName(loginUser.getUname());
      if (loginUserDTO == null) {
         model.addAttribute("errMsg", "用户不存在！");
         return "ordinary/signin";
      } else {
         BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
         if (!bCryptPasswordEncoder.matches(loginUser.getPassword(), loginUserDTO.getPassword())) {
            model.addAttribute("errMsg", "密码错误！");
            return "ordinary/signin";
         } else {
            loginUser.setCapacity(loginUserDTO.getCapacity());
            loginUser.setUid(loginUserDTO.getUid());
            loginUser.setHeadPic(loginUserDTO.getHeadPic());
            session.setAttribute("userMsg", loginUser);
            Pager<BlogDTO> result = this.blogService.queryBlogByPage(1, 5);
            model.addAttribute("blogDTOS", result);
            String beforeSignin = (String)session.getAttribute("beforeSignin");
            session.removeAttribute("beforeSignin");
            return "redirect:" + beforeSignin;
         }
      }
   }

   @RequestMapping({"/signout"})
   public String signout(HttpSession session, Model model) {
      session.removeAttribute("userMsg");
      Pager<BlogDTO> result = this.blogService.queryBlogByPage(1, 5);
      model.addAttribute("blogDTOS", result);
      return "redirect:blog";
   }

   @RequestMapping({"/signup"})
   public String signup(RegisterDTO registerDTO, HttpSession session, Model model) throws ParseException {
      this.userService.addUser(registerDTO);
      LoginUserDTO loginUserDTO = this.loginService.queryUserByName(registerDTO.getUsername());
      session.setAttribute("userMsg", loginUserDTO);
      Pager<BlogDTO> result = this.blogService.queryBlogByPage(1, 5);
      model.addAttribute("blogDTOS", result);
      return "ordinary/article";
   }

   @RequestMapping(
      value = {"/checkUser/{username}"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String checkUser(@PathVariable("username") String username) {
      LoginUserDTO loginUserDTO = this.loginService.queryUserByName(username);
      Map<String, Integer> map = new HashMap();
      if (loginUserDTO != null && !StringUtils.isEmpty(loginUserDTO.getUname())) {
         map.put("bo", 1);
      } else {
         map.put("bo", 0);
      }

      String s = JSON.toJSONString(map);
      return s;
   }

   @RequestMapping(
      value = {"/checkOldPwd/{username}/{oldPwd}"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String checkOldPwd(@PathVariable("username") String username, @PathVariable("oldPwd") String oldPwd) {
      LoginUserDTO loginUserDTO = this.loginService.queryUserByName(username);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      Map<String, Integer> map = new HashMap();
      if (bCryptPasswordEncoder.matches(oldPwd, loginUserDTO.getPassword())) {
         map.put("bo", 0);
      } else {
         map.put("bo", 1);
      }

      String s = JSON.toJSONString(map);
      return s;
   }

   @RequestMapping({"/blog"})
   public String blog(Model model) {
      Pager<BlogDTO> result = this.blogService.queryBlogByPage(1, 5);
      model.addAttribute("blogDTOS", result);
      return "ordinary/article";
   }

   @RequestMapping(
      value = {"/comments"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String comments(int id) {
      Pager<CommentShowDTO> shows = this.commentsService.queryByBlogId(id);
      return JSON.toJSONString(shows);
   }

   @RequestMapping(
      value = {"/capacityShow"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String capacityShow(HttpServletResponse response) throws IOException {
      List<Category> allCategory = this.loginService.getAllCategory();
      return JSON.toJSONString(allCategory);
   }

   public List<BlogDTO> allBlog() {
      List<Blog> blogs = this.blogService.queryAllBlog();
      List<BlogDTO> blogDTOS = new ArrayList();
      Iterator var3 = blogs.iterator();

      while(var3.hasNext()) {
         Blog blog = (Blog)var3.next();
         BlogDTO blogDTO = new BlogDTO();
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
         simpleDateFormat.format(blog.getReleaseDate());
         blogDTO = BlogUtils.BolgDateToYMD(blog, blogDTO);
         BeanUtils.copyProperties(blog, blogDTO);
         Category category = this.categoryService.getCategoryById(blog.getCategoryId());
         blogDTO.setCatecoty(category.getName());
         int count = this.commentsService.queryCountByBlogId(blog.getId());
         blogDTO.setCount(count);
         User user = this.userService.queryUserById(blog.getUid());
         blogDTO.setAuthor(user.getUname());
         blogDTOS.add(blogDTO);
      }

      return blogDTOS;
   }
}
