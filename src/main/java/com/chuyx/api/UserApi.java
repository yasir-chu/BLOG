package com.chuyx.api;

import com.chuyx.wrapper.CommentWrapper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yasir.chu
 * date 2021/1/27
 **/
@Api(tags = "用户模块")
@ResponseBody
public interface UserApi {

    /**
     * 检查用户名是否重复
     * @param username 用户名
     * @return 检查结果
     */
    @PostMapping(value = "/user/checkUser/{username}", produces = {"application/json;charset=utf-8"})
    String saveComment(@PathVariable("username") String username);


}
