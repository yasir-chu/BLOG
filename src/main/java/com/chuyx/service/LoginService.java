package com.chuyx.service;

import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.po.Category;

import java.util.List;

public interface LoginService {
    LoginUserDTO loginCheck(LoginUserDTO loginUser);

    List<Category> getAllCategory();

    LoginUserDTO queryUserByName(String name);
}
