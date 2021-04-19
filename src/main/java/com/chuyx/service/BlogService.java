package com.chuyx.service;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.PublishBlogDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.wrapper.BlogWrapper;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * @author yasir.chu
 */
public interface BlogService {

   /**
    * 更新博客访问量
    * @param visitCount 更新值
    * @param id 博客id
    * @return 更新数量
    */
   Integer updateBlogVisitCount(Integer visitCount, Integer id);

   /**
    * 后台管理用
    * @param index 当前页
    * @param size 页面大小
    * @return 数据集
    */
   Pager<BlogDTO> queryBlogByPage(int index, int size);

   /**
    * 根据名称模糊搜索
    * @param name 博客名
    * @return 查询结果
    */
   List<BlogDTO> queryBlogSearch(String name);


   /**
    * 根据作者分页查询博客
    * @param uid 作者id
    * @param page 当前页
    * @return 查询结果
    */
   Pager<BlogDTO> queryBlogByPageAuthor(Integer uid,Integer page);

   /**
    * 删除博客
    * @param id 博客id
    * @return 删除数量
    */
   Integer deleteBlog(Integer id);

   /**
    * 删除博客
    * @param id 博客id
    * @param uid 用户id
    * @return 删除数量
    */
   Integer deleteBlog(Integer id, Integer uid);

   /**
    * 查询所有博客总数
    * @return 博客总数
    */
   Integer getAllBlogSize();

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
    * @param uid 用户id
    * @return 0为失败，其他为成功
    */
   Integer save(BlogWrapper.SaveBlogDTO req, Integer uid);

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

   /**
    * 根据博客id集合获取博客
    * @param blogIdList 博客id集合
    * @return 博客id 博客名
    */
   Map<Integer, String> getBlogIdNameMap(List<Integer> blogIdList);

   /**
    * 检查该类别下是否有博客
    * @param id 类别id
    * @return true 有
    */
    boolean checkBlogInCategory(Integer id);
}
