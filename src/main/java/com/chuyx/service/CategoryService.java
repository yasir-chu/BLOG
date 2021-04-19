package com.chuyx.service;

import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Category;

import java.util.List;
import java.util.Map;

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

   /**
    * 根据id获取
    * @param categoryList 类别id集合
    * @return 类别id 类别名
    */
   Map<Integer, String> getCategoryIdNameMap(List<Integer> categoryList);

   /**
    * 分页获取类别
    * @param page 当前页面
    * @param size 页面大小
    * @return 分页信息
    */
    Pager<Category> queryPage(Integer page, Integer size);

   /**
    * 删除类别
    * @param id 类别id
    * @return 状态
    */
   Integer delCategory(Integer id);

   /**
    * 新增类别
    * @param name 类别名
    * @param id 类别id 有修改 没有新增
    */
   void saveCategory(Integer id,String name);
}
