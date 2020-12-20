package com.chuyx.service;

import com.chuyx.pojo.model.Category;

import java.util.List;

public interface CategoryService {


   Category getCategoryById(int id);

   /**
    * 获取所有的分类
    *
    * @return 分类列表
    */
   List<Category> getAllCategory();
}
