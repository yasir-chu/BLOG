package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.OrdinaryApi;
import com.chuyx.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author chuyx
 * @data 2020-12-20
 */
@Controller
public class NewOrdinaryController implements OrdinaryApi {

    @Autowired
    private CategoryService categoryService;


    @Override
    public String getAllOrdinary() {
        return JSON.toJSONString(categoryService.getAllCategory());
    }
}
