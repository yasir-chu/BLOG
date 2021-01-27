package com.chuyx.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chuyx
 * @data 2020-12-20
 */
@Api(tags = "分类模块")
@ResponseBody
public interface CapacityApi {

    /**
     * 获取所有的分类类别
     *
     * @return 获取所有的分类类别
     */
    @PostMapping(value = "/capacity/getAllOrdinary", produces = {"application/json;charset=utf-8"})
    @ApiOperation("获取所有的分类类别")
    String getAllCapacity();

}
