package com.chuyx.mapper;

import com.chuyx.pojo.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryMapper {
    List<Category> getAllCategory();

    Category getCategoryById(int id);
}
