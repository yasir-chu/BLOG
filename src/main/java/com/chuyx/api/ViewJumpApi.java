package com.chuyx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Api(tags = "页面跳转模块")
public interface ViewJumpApi {

    /**
     * 获取访问量前10的博客
     * @return 访问量前10的博客
     */
    @RequestMapping(value = "/views/blogPage")
    String blogPage();
}
