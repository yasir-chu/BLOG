package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author cyx
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

   /**
    * 根据用户名获取检验对象
    * @param username 用户名
    * @return 检验对象
    */
   LoginUserDTO queryUserByUsername(String username);


}
