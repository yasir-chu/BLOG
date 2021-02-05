package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.dto.UpdateUserDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.BlogBaseVO;
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

import com.chuyx.wrapper.BlogWrapper;
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

   @RequestMapping(
      value = {"/serach"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String serachBlog(String name) {
      List<BlogDTO> blogDTOS = this.blogService.queryBlogSearch(name);
      return JSON.toJSONString(blogDTOS);
   }

   @RequestMapping({"/toapply"})
   public String toapply() {
      return "ordinary/apply";
   }

   @RequestMapping({"/apply"})
   public String apply(HttpSession session, String editorContent, Model model) {
      LoginUserDTO userMsg = (LoginUserDTO)session.getAttribute("userMsg");
      int uid = userMsg.getUid();
      this.userService.applyBlogUpdate(uid);
      String uname = this.userService.queryUserById(uid).getUname();
      this.emailService.sentEmailToMy(editorContent, uid);
      LoginUserDTO loginUserDTO = (LoginUserDTO)session.getAttribute("userMsg");
      loginUserDTO.setCapacity(-1);
      session.removeAttribute("userMsg");
      session.setAttribute("userMsg", loginUserDTO);
      return "ordinary/suc";
   }
}
