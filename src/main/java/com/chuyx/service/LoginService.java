package com.chuyx.service;

import com.chuyx.entity.dto.LoginUserDTO;

public interface LoginService {
   LoginUserDTO loginCheck(LoginUserDTO loginUser);


   LoginUserDTO queryUserByName(String name);
}
