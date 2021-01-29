package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.api.UserApi;
import com.chuyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author chuyx
 * @data 2021-01-29
 */
@Controller
public class NewUserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public String saveComment(String username) {
        return JSON.toJSONString(userService.checkUsername(username));
    }
}
