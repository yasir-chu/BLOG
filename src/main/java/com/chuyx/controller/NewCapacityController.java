package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.CapacityApi;
import com.chuyx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author chuyx
 * @data 2020-12-20
 */
@Controller
public class NewCapacityController implements CapacityApi {

    @Autowired
    private CategoryService categoryService;


    @Override
    public String getAllCapacity() {
        return JSON.toJSONString(categoryService.getAllCategory());
    }
}
