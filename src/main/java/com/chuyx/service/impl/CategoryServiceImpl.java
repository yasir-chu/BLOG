package com.chuyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chuyx.mapper.CategoryMapper;
import com.chuyx.pojo.model.Category;
import com.chuyx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
    CategoryMapper categoryMapper;

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
}
