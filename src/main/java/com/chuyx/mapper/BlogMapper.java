package com.chuyx.mapper;

import com.chuyx.pojo.dto.NewBlogDTO;
import com.chuyx.pojo.model.Blog;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BlogMapper {
   List<Blog> queryAllBlog();

   int queryAllBlogSize();

   List<Blog> queryBlogByCateId(int categoryId);

   List<Blog> queryHotBlog();

   List<Blog> queryNewBlog();

   Blog queryBlogById(int id);

   void updateBlogVisitCount(Blog blog);

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
