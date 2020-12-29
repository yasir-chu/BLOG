package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.entity.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
   Category getCategoryById(int id);
}
