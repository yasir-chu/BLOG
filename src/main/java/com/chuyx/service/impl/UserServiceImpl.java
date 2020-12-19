package com.chuyx.service.impl;

import com.chuyx.mapper.LogMapper;
import com.chuyx.mapper.UserMapper;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.dto.UpdateUserDTO;
import com.chuyx.pojo.po.Log;
import com.chuyx.pojo.po.User;
import com.chuyx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yasir.chu
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LogMapper logMapper;

    @Override
    public int addUser(RegisterDTO registerDTO) throws ParseException {
        User user = new User();
        user.setUname(registerDTO.getUsername());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(registerDTO.getPassword()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date brith = simpleDateFormat.parse(registerDTO.getBrith());
        user.setBrith(brith);
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        user.setSex(registerDTO.getSex());
        return userMapper.addUser(user);
    }

    @Override
    public User queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public LoginUserDTO queryUserByUserName(String userName) {
        return userMapper.queryUserByUsername(userName);
    }

    @Override
    public int updateUserMsg(UpdateUserDTO updateUserDTO) {
        User user = new User();
        user.setHeadPic(updateUserDTO.getHeadPic());
        user.setUname(updateUserDTO.getUsername());
        user.setPhone(updateUserDTO.getPhone());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;

        try {
            parse = simpleDateFormat.parse(updateUserDTO.getBrith());
        } catch (ParseException var7) {
            var7.printStackTrace();
        }

        user.setBrith(parse);
        user.setEmail(updateUserDTO.getEmail());
        user.setSex(updateUserDTO.getSex());
        user.setUid(updateUserDTO.getUid());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(updateUserDTO.getPassword());
        user.setPassword(encode);
        userMapper.updateUserMsg(user);
        return 0;
    }

    @Override
    public int applyBlogUpdate(int uid) {
        userMapper.applyBlogUpdate(uid);
        Log log = new Log();
        log.setConnent("用户" + uid + "申请博主资格");
        log.setUid(uid);
        log.setEvent("博主申请");
        logMapper.inserntLog(log);
        return 0;
    }

    @Override
    public int getCountUserSize() {
        return userMapper.getUsersSize();
    }

    @Override
    public int getCountAuthorSize() {
        return userMapper.getAuthorSize();
    }

    @Override
    public List<LoginUserDTO> getWaitAuthorPage(int page, int size) {
        int index = (page - 1) * size;
        List<User> users = userMapper.getWaitAuthor(index, size);
        return usersToLoginUsers(users);
    }

    @Override
    public int getCountWaitAuthor() {
        return userMapper.getCountWaitAuthor();
    }

    @Override
    public int passAuthor(int uid) {
        return userMapper.passAuthor(uid);
    }

    @Override
    public List<User> getAllUser(int page, int size) {
        int index = (page - 1) * size;
        return userMapper.getAllUser(index, size);
    }

    @Override
    public int delUser(int id) {
        return userMapper.delUser(id);
    }

    public List<LoginUserDTO> usersToLoginUsers(List<User> users) {
        List<LoginUserDTO> loginUserDTOS = new ArrayList(users.size());
        for (User user : users) {
            LoginUserDTO loginUserDTO = new LoginUserDTO();
            loginUserDTO.setUid(user.getUid());
            loginUserDTO.setUname(user.getUname());
            loginUserDTO.setCapacity(user.getCapacity());
            loginUserDTOS.add(loginUserDTO);
        }

        return loginUserDTOS;
    }
}
