package com.chuyx.service.impl;

import com.chuyx.mapper.CategoryMapper;
import com.chuyx.pojo.model.Category;
import com.chuyx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
   @Autowired
   CategoryMapper categoryMapper;

   public Category getCategoryById(int id) {
      return this.categoryMapper.getCategoryById(id);
   }
}
