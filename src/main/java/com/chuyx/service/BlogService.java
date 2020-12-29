package com.chuyx.service;

import com.chuyx.entity.dto.BlogDTO;
import com.chuyx.entity.dto.Pager;
import com.chuyx.entity.dto.PublishBlogDTO;
import com.chuyx.entity.po.Blog;
import java.util.List;

/**
 * @author yasir.chu
 */
public interface BlogService {
   List<Blog> queryAllBlog();

   int queryAllBlogSize();

   List<Blog> queryBlogByCateId(int categoryId);

   List<Blog> queryNewBlog();

   Blog queryBlogById(int id);

   void updateBlogVisitCount(Blog blog);

   List<Blog> queryCapacityBlog(int capaId);

   Pager<BlogDTO> queryBlogByPage(int index, int size);

   Pager<BlogDTO> queryBlogByPageCata(int cataId, int page, int size);

   List<BlogDTO> queryBlogSearch(String name);

   int addBlog(PublishBlogDTO publishBlogDTO, int uid);

   int updateBlog(PublishBlogDTO publishBlogDTO, int uid);

   Pager<BlogDTO> queryBlogByPageAuthord(int uid);

   Pager<BlogDTO> queryBlogByPageAuthordPage(int uid, int page);

   int deleteBlog(int id);

   int getAllBlogSize();

   /**
    * 获取访问量最多的10篇博客
    *
    * @return 博客列表
    */
   List<Blog> getHotBlog();

   /**
    * 获取最新的10篇博客
    *
    * @return 博客列表
    */
   List<Blog> getNewestBlog();
}
