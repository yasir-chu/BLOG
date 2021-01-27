package com.chuyx.service.impl;

import com.chuyx.mapper.CategoryMapper;
import com.chuyx.mapper.UserMapper;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.model.Category;
import com.chuyx.service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
   @Autowired
   UserMapper userMapper;

   @Override
   public LoginUserDTO loginCheck(LoginUserDTO loginUser) {
      return null;
   }


   @Override
   public LoginUserDTO queryUserByName(String name) {
      return this.userMapper.queryUserByUsername(name);
   }
}
