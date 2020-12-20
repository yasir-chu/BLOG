package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
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
