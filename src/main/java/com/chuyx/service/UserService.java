package com.chuyx.service;

import com.chuyx.entity.dto.LoginUserDTO;
import com.chuyx.entity.dto.RegisterDTO;
import com.chuyx.entity.dto.UpdateUserDTO;
import com.chuyx.entity.po.User;
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
