package com.chuyx.service;

import com.chuyx.pojo.model.Category;

import java.util.List;

/**
 * @author yasir.chu
 */
public interface CategoryService {


   /**
    * 根据类别id找到类别名
    *
    * @param id 类别id
    * @return 查询结果
    */
   Category getCategoryById(int id);

   /**
    * 获取所有的分类
    *
    * @return 分类列表
    */
   List<Category> getAllCategory();
}
