package com.chuyx.service;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.PublishBlogDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.wrapper.BlogWrapper;

import java.util.List;

/**
 * @author yasir.chu
 */
public interface BlogService {

   int queryAllBlogSize();


   /**
    * 更新博客访问量
    * @param visitCount 更新值
    */
   Integer updateBlogVisitCount(Integer visitCount, Integer id);

   Pager<BlogDTO> queryBlogByPage(int index, int size);


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

   /**
    * 分页获取博客
    * @param req 分页信息
    * @return 分页结果
    */
   Pager<BlogBaseVO> queryPageBlog(BlogWrapper.QueryPageDTO req);

   /**
    * 获取一篇博客
    *
    * @param id 博客id
    * @return 博客列表
    */
   BlogBaseVO queryBlogById(Integer id);

   /**
    * 保存博客
    * @param req 博客信息
    * @return 0为失败，其他为成功
    */
   Integer save(BlogWrapper.SaveBlogDTO req);

   /**
    * 删除博客  软删除
    * @param id 博客id
    * @return 删除数量  为0就是失败
    */
   Integer softDeleteBlog(Integer id);

   /**
    * 根据内容搜索博客
    * @param comment 搜索内容
    * @return 博客列表 只查10个防止bug
    */
   List<BlogBaseVO> searchBlogByComment(String comment);
}
