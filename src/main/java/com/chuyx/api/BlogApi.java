package com.chuyx.api;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yasir.chu
 * date 2020/12/19
 * @InterfaceName: BlogApi
 **/
@Api(tags = "博客API")
@RequestMapping(produces = {"application/json;charset=utf-8"})
@ResponseBody
public interface BlogApi {

    /**
     * 分页查询博客
     *
     * @return 博客分页信息
     */
    @ApiOperation("分页查询博客")
    @PostMapping("/blog/queryPage")
    Pager<BlogDTO> queryPage();
}
