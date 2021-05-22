package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.AdminApi;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.ErrorVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import com.chuyx.wrapper.BlogWrapper;
import com.chuyx.wrapper.UserWrapper;
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

   @Autowired
   private CategoryService categoryService;

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
   public String refuseAuthor(Integer uid) {
      userService.refuseAuthor(uid);
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

   @Override
   public String queryPageCategory(Integer page) {
      return JSON.toJSONString(categoryService.queryPage(page, NormalConstant.TOP_SIZE));
   }

   @Override
   public String delCategory(Integer id) {
      Integer integer = categoryService.delCategory(id);
      if (integer < NormalConstant.ZERO){
         return JSON.toJSONString(new ErrorVO(NormalConstant.ERROR_CODE, "该类别下存在博客，不允许删除"));
      }
      return queryPageCategory(NormalConstant.ONE);
   }

   @Override
   public String saveCategory(Integer id, String name) {
      categoryService.saveCategory(id, name);
      return queryPageCategory(NormalConstant.ONE);
   }

   @Override
   public String searchUser(UserWrapper.SearchUserDTO searchUserDTO) {
      return JSON.toJSONString(userService.searchUser(searchUserDTO));
   }

   @Override
   public String searchBlog(BlogWrapper.SearchBlogDTO searchBlogDTO) {
      return JSON.toJSONString(blogService.searchBlog(searchBlogDTO));
   }
}
