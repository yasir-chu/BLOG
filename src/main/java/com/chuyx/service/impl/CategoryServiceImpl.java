package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chuyx.mapper.CategoryMapper;
import com.chuyx.pojo.model.Category;
import com.chuyx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
   @Autowired
   CategoryMapper categoryMapper;

   @Override
   public Category getCategoryById(int id) {
      return this.categoryMapper.getCategoryById(id);
   }

   @Override
   public List<Category> getAllCategory() {
      QueryWrapper<Category> wrapper = new QueryWrapper<>();
      List<Category> categories = categoryMapper.selectList(wrapper);
      return CollectionUtils.isEmpty(categories) ? Collections.emptyList() : categories;
   }
}
