package com.chuyx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Api(tags = "页面跳转模块")
public interface ViewJumpApi {

    /**
     * 获取首页博客数据
     *
     * @param model 存数据的模型
     * @return 首页博客
     */
    @RequestMapping(value = "/views/blogPage")
    String blogPage(Model model);
}
