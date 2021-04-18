package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.AdminApi;
import com.chuyx.constant.NormalConstant;
import com.chuyx.service.BlogService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author cyx
 */
@Controller
public class AdminController implements AdminApi {


   @Autowired
   private BlogService blogService;

   @Autowired
   private UserService userService;

   @Autowired
   private CommentsService commentsService;

   @Override
   public String queryPageWaitPassAuthor(Integer page) {
      return JSON.toJSONString(userService.getWaitAuthorPage(page, NormalConstant.TOP_SIZE));
   }

   @Override
   public String passAuthor(Integer uid) {
      userService.passAuthor(uid);
      return queryPageWaitPassAuthor(NormalConstant.ONE);
   }

   @Override
   public String queryPageComments(Integer page) {
      return JSON.toJSONString(commentsService.getPageCommentsSize(page, NormalConstant.TOP_SIZE));
   }

   @Override
   public String delComments(Integer id) {
      commentsService.delComment(id);
      return queryPageComments(NormalConstant.ONE);
   }

   @Override
   public String queryPageBlog(Integer page) {
      return JSON.toJSONString(blogService.queryBlogByPage(page, NormalConstant.TOP_SIZE));
   }

   @Override
   public String delBlog(Integer id) {
      blogService.deleteBlog(id);
      return queryPageBlog(NormalConstant.ONE);
   }

   @Override
   public String queryPageUser(Integer page) {
      return JSON.toJSONString(userService.getPageUser(page, NormalConstant.TOP_SIZE));
   }

   @Override
   public String delUser(Integer id) {
      userService.delUser(id);
      return queryPageUser(NormalConstant.ONE);
   }
}
