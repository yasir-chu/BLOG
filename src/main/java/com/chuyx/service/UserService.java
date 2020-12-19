package com.chuyx.service;

import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.dto.UpdateUserDTO;
import com.chuyx.pojo.po.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    int addUser(RegisterDTO registerDTO) throws ParseException;

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
}
