package com.chuyx.mapper;

import com.chuyx.pojo.model.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CategoryMapper {
   List<Category> getAllCategory();

   Category getCategoryById(int id);
}
