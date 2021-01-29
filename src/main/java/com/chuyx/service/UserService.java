package com.chuyx.service;

import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.dto.UpdateUserDTO;
import com.chuyx.pojo.model.User;
import com.chuyx.wrapper.UserWrapper;

import java.text.ParseException;
import java.util.List;

public interface UserService {
   User queryUserById(int id);

   LoginUserDTO queryUserByUserName(String userName);

   int updateUserMsg(UpdateUserDTO updateUserDTO);

   int applyBlogUpdate(int uid);

   int getCountUserSize();

   int getCountAuthorSize();

   List<LoginUserDTO> getWaitAuthorPage(int page, int size);

   int getCountWaitAuthor();

   int passAuthor(int uid);

   List<User> getAllUser(int page, int size);

   int delUser(int id);

   /**
    * 根据用户id评论查找用户信息
    *
    * @param ids 用户ids
    * @return 用户信息
    */
   List<User> queryUsers(List<Integer> ids);

   /**
    * 检查用户名
    * @param username 用户名
    * @return 检查结果
    */
   String checkUsername(String username);

   /**
    * 用户注册
    * @param saveDTO 用户信息
    * @return 用户信息
    */
   User saveUser(UserWrapper.SaveDTO saveDTO);

   /**
    * 用户登录
    * @param loginUser 登录信息
    * @return 返回结果
    */
   LoginUserDTO signIn(LoginUserDTO loginUser);
}
