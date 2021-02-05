package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.pojo.dto.NewBlogDTO;
import com.chuyx.pojo.model.Blog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogMapper extends BaseMapper<Blog> {


   /**
    * 统计博客总数量
    *
    * @return 博客总数量
    */
    int queryAllBlogSize();

   /**
    * 更新博客访问量
    */
    Integer updateBlogVisitCount(@Param("visitCount") Integer visitCount, @Param("id") Integer id);

    List<Blog> queryCapacityBlog(int capaId);

    List<Blog> queryBlogByPage(int index, int size);

    int countSize();

    List<Blog> queryBlogByPageCata(int cataId, int index, int size);

    int countSizeCata(int id);

    List<Blog> queryBlogSearch(String name);

    int addBlog(NewBlogDTO newBlogDTO);

    List<Blog> queryBlogByAuthorId(int uid);

    int queryBlogByAuthorIdCount(int uid);

    List<Blog> queryBlogByAuthorIdPage(int uid, int index, int pageSize);

    int delBlog(int id);

    int updateBlog(NewBlogDTO newBlogDTO);
}
