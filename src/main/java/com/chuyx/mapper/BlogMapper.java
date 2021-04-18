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
  * 更新博客访问量
  * @param visitCount 访问量
  * @param id 博客id
  * @return 更新数量
  */
    Integer updateBlogVisitCount(@Param("visitCount") Integer visitCount, @Param("id") Integer id);

}
