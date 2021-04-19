package com.chuyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.CategoryMapper;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Category;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.utils.NormalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author cyx
 */
@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BlogService blogService;

    @Autowired
    private Jedis jedis;

    private static final String CATEGORY_ALL_REDIS = "category.all";

    @Override
    public Category getCategoryById(int id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<Category> getAllCategory() {
        if (jedis.get(CATEGORY_ALL_REDIS) != null) {
            String allCategory = jedis.get(CATEGORY_ALL_REDIS);
            return JSON.parseArray(allCategory, Category.class);
        }
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptyList();
        }
        String allCategory = JSON.toJSONString(categories);
        jedis.set(CATEGORY_ALL_REDIS, allCategory);
        return CollectionUtils.isEmpty(categories) ? Collections.emptyList() : categories;
    }

    @Override
    public Map<Integer, String> getCategoryIdNameMap(List<Integer> categoryList) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", categoryList);
        List<Category> categories = categoryMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(categories)) {
            return Collections.emptyMap();
        }
        return categories.stream().collect(Collectors.toMap(Category::getId, Category::getName, (ok, nk) -> ok));
    }

    @Override
    public Pager<Category> queryPage(Integer page, Integer size) {
        Page<Category> mpPage = new Page<>(page, size);
        Page<Category> categoryPage = categoryMapper.selectPage(mpPage, null);
        if (CollectionUtils.isEmpty(categoryPage.getRecords())) {
            return NormalUtils.pagerRows(categoryPage, null);
        }
        return NormalUtils.pagerRows(categoryPage, categoryPage.getRecords());
    }

    @Override
    public Integer delCategory(Integer id) {
        if (blogService.checkBlogInCategory(id)) {
            return NormalConstant.DOWN_ONE;
        }
        return categoryMapper.deleteById(id);
    }

    @Override
    public void saveCategory(Integer id, String name) {
        Category category = new Category();
        if (StringUtils.isEmpty(name)){
            return;
        }
        jedis.del(CATEGORY_ALL_REDIS);
        category.setName(name);
        if (id == null || id <= 0) {
            category.setId(null);
            categoryMapper.insert(category);
            return;
        }
        UpdateWrapper<Category> categoryUpdateWrapper = new UpdateWrapper<>();
        categoryUpdateWrapper.eq("id", id).set("name", name);
        categoryMapper.update(null, categoryUpdateWrapper);
    }
}
