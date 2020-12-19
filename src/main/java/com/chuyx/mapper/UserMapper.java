package com.chuyx.mapper;

import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryAll();

    LoginUserDTO queryUserByUsername(String username);

    int addUser(User user);

    User queryUserById(int id);

    int updateUserMsg(User user);

    int applyBlogUpdate(int uid);

    int getUsersSize();

    int getAuthorSize();

    List<User> getWaitAuthor(int index, int size);

    int getCountWaitAuthor();

    int passAuthor(int uid);

    List<User> getAllUser(int index, int size);

    int delUser(int id);
}
