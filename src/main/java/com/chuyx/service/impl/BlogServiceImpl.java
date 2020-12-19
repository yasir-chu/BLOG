package com.chuyx.service.impl;

import com.chuyx.mapper.BlogMapper;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.NewBlogDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.PublishBlogDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import com.chuyx.utils.BlogUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
   @Autowired
   BlogMapper blogMapper;
   @Autowired
   CategoryService categoryService;
   @Autowired
   CommentsService commentsService;
   @Autowired
   UserService userService;

   public List<Blog> queryAllBlog() {
      return this.blogMapper.queryAllBlog();
   }

   public int queryAllBlogSize() {
      return this.blogMapper.queryAllBlogSize();
   }

   public List<Blog> queryBlogByCateId(int categoryId) {
      return this.blogMapper.queryBlogByCateId(categoryId);
   }

   public List<Blog> queryHotBlog() {
      return this.blogMapper.queryHotBlog();
   }

   public List<Blog> queryNewBlog() {
      return this.blogMapper.queryNewBlog();
   }

   public Blog queryBlogById(int id) {
      return this.blogMapper.queryBlogById(id);
   }

   public void updateBlogVisitCount(Blog blog) {
      this.blogMapper.updateBlogVisitCount(blog);
   }

   public List<Blog> queryCapacityBlog(int capaId) {
      return this.blogMapper.queryCapacityBlog(capaId);
   }

   public Pager<BlogDTO> queryBlogByPage(int page, int size) {
      int index = (page - 1) * size;
      List<Blog> blogs = this.blogMapper.queryBlogByPage(index, size);
      Pager<BlogDTO> result = new Pager();
      List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
      result.setRows(blogDTOS);
      int countSize = this.blogMapper.countSize();
      result.setTotal((long)countSize);
      result.setPage(page);
      if (countSize < size) {
         result.setSize(1);
      } else if (countSize % size > 0) {
         result.setSize(countSize / size + 1);
      } else {
         result.setSize(countSize / size);
      }

      return result;
   }

   public Pager<BlogDTO> queryBlogByPageCata(int cataId, int page, int size) {
      int index = (page - 1) * 5;
      List<Blog> blogs = this.blogMapper.queryBlogByPageCata(cataId, index, size);
      Pager<BlogDTO> result = new Pager();
      List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
      result.setRows(blogDTOS);
      int countSize = this.blogMapper.countSizeCata(cataId);
      result.setTotal((long)countSize);
      result.setPage(page);
      if (countSize < 5) {
         result.setSize(1);
      } else if (countSize / 5 > 0) {
         result.setSize(countSize / 5 + 1);
      } else {
         result.setSize(countSize / 5);
      }

      return result;
   }

   public List<BlogDTO> queryBlogSearch(String name) {
      char[] chars = name.toCharArray();
      String name2 = "%" + name + "%";
      List<Blog> blogs = this.blogMapper.queryBlogSearch(name2);
      List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
      Iterator var6 = blogDTOS.iterator();

      while(var6.hasNext()) {
         BlogDTO blogDTO = (BlogDTO)var6.next();
         blogDTO.setTitle(blogDTO.getTitle().replaceAll(name, "<b style='color:#6bc30d'>" + name + "</b>"));
      }

      return blogDTOS;
   }

   public int addBlog(PublishBlogDTO publishBlogDTO, int uid) {
      NewBlogDTO newBlogDTO = new NewBlogDTO();
      newBlogDTO.setTitle(publishBlogDTO.getTitle());
      newBlogDTO.setSmallTitle(publishBlogDTO.getSmallTitle());
      newBlogDTO.setCatecoty(publishBlogDTO.getCapacity());
      newBlogDTO.setContent(publishBlogDTO.getContent());
      newBlogDTO.setUid(uid);
      newBlogDTO.setAuthor(this.userService.queryUserById(uid).getUname());
      newBlogDTO.setRepleseaDate(new Date());
      this.blogMapper.addBlog(newBlogDTO);
      return 0;
   }

   public int updateBlog(PublishBlogDTO publishBlogDTO, int uid) {
      NewBlogDTO newBlogDTO = new NewBlogDTO();
      newBlogDTO.setTitle(publishBlogDTO.getTitle());
      newBlogDTO.setSmallTitle(publishBlogDTO.getSmallTitle());
      newBlogDTO.setCatecoty(publishBlogDTO.getCapacity());
      newBlogDTO.setContent(publishBlogDTO.getContent());
      newBlogDTO.setUid(uid);
      newBlogDTO.setAuthor(this.userService.queryUserById(uid).getUname());
      newBlogDTO.setId(publishBlogDTO.getId());
      this.blogMapper.updateBlog(newBlogDTO);
      return 0;
   }

   public Pager<BlogDTO> queryBlogByPageAuthord(int uid) {
      Pager<BlogDTO> result = new Pager();
      List<Blog> blogs = this.blogMapper.queryBlogByAuthorId(uid);
      List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
      result.setRows(blogDTOS);
      int count = this.blogMapper.queryBlogByAuthorIdCount(uid);
      result.setTotal((long)count);
      result.setPage(1);
      if (count <= 10) {
         result.setSize(1);
      } else if (count % 10 > 0) {
         result.setSize(count / 10 + 1);
      } else {
         result.setSize(count / 10);
      }

      return result;
   }

   public Pager<BlogDTO> queryBlogByPageAuthordPage(int uid, int page) {
      Pager<BlogDTO> result = new Pager();
      int index = (page - 1) * 10;
      List<Blog> blogs = this.blogMapper.queryBlogByAuthorIdPage(uid, index, 10);
      List<BlogDTO> blogDTOS = this.pageBlogUtil(blogs);
      result.setRows(blogDTOS);
      int count = this.blogMapper.queryBlogByAuthorIdCount(uid);
      result.setTotal((long)count);
      result.setPage(page);
      if (count <= 10) {
         result.setSize(1);
      } else if (count % 10 > 0) {
         result.setSize(count / 10 + 1);
      } else {
         result.setSize(count / 10);
      }

      return result;
   }

   public int deleteBlog(int id) {
      this.blogMapper.delBlog(id);
      this.commentsService.delCommentByBlogId(id);
      return 0;
   }

   public int getAllBlogSize() {
      return this.blogMapper.countSize();
   }

   public List<BlogDTO> pageBlogUtil(List<Blog> blogs) {
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
