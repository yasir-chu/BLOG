package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.LogMapper;
import com.chuyx.mapper.UserMapper;
import com.chuyx.pojo.dto.AdminUser;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.UpdateUserDTO;
import com.chuyx.pojo.model.Log;
import com.chuyx.pojo.model.User;
import com.chuyx.service.UserService;
import com.chuyx.utils.DateUtils;
import com.chuyx.utils.DozerUtil;
import com.chuyx.utils.NormalUtils;
import com.chuyx.wrapper.UserWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cyx
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
   @Autowired
   UserMapper userMapper;
   @Autowired
   LogMapper logMapper;

   @Override
   public void applyBlogUpdate(Integer uid, String logged) {
      UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
      updateWrapper.eq("uid",uid).set("logged", logged).set("capacity", NormalConstant.DOWN_ONE);
      userMapper.update(null, updateWrapper);
      Log log = new Log();
      log.setConnent("用户" + uid + "申请博主资格");
      log.setUid(uid);
      log.setEvent("博主申请");
      logMapper.insert(log);
   }

   @Override
   public Integer getCountUserSize() {
      return userMapper.selectCount(new QueryWrapper<>());
   }

   @Override
   public Integer getCountAuthorSize() {
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("capacity", NormalConstant.ONE);
      return this.userMapper.selectCount(queryWrapper);
   }

   @Override
   public Pager<LoginUserDTO> getWaitAuthorPage(int page, int size) {
      Page<User> pager = new Page<>(page, size);
      QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
      userQueryWrapper.eq("capacity", NormalConstant.DOWN_ONE);
      Page<User> userPage = userMapper.selectPage(pager, userQueryWrapper);
      Pager<LoginUserDTO> result = new Pager<>();
      result.setTotal(userPage.getTotal());
      result.setPage(page);
      result.setRows(DozerUtil.mapList(userPage.getRecords(), LoginUserDTO.class));
      int allPageSize = Integer.parseInt(String.valueOf(userPage.getTotal()))/size;
      if (userPage.getTotal()%size > 0){
         allPageSize += 1;
      }
      result.setSize(allPageSize);
      return result;
   }

   @Override
   public Integer passAuthor(int uid) {
      User user = new User();
      user.setUid(uid);
      user.setCapacity(NormalConstant.ONE);
      return userMapper.updateById(user);
   }

   @Override
   public Pager<AdminUser> getPageUser(int page, int size) {
      Page<User> userPage = new Page<>(page, size);
      QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
      userQueryWrapper.eq("del", NormalConstant.ZERO);
      userQueryWrapper.ne("capacity", NormalConstant.TWE);
      Page<User> userPager = userMapper.selectPage(userPage, userQueryWrapper);
      if (CollectionUtils.isEmpty(userPager.getRecords())){
         return  NormalUtils.pagerRows(userPager, null);
      }
      return NormalUtils.pagerRows(userPager, mapListAdminUser(userPager.getRecords()));
   }

   /**
    * 数据转换
    * @param records 待转换数据
    * @return 转换后数据
    */
   private List<AdminUser> mapListAdminUser(List<User> records) {
      return records.stream().map(a -> {
         AdminUser item = new AdminUser();
         item.setUname(a.getUname());
         item.setEmail(a.getEmail());
         item.setBrith(DateUtils.dateToString(a.getBrith()));
         item.setCapacity(a.getCapacity());
         item.setUid(a.getUid());
         return item;
      }).collect(Collectors.toList());
   }

   @Override
   public Integer delUser(Integer id) {
      QueryWrapper<User> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("uid", id);
      User user = new User();
      user.setUid(id);
      user.setDel(NormalConstant.DOWN_ONE);
      return userMapper.update(user, queryWrapper);
   }

   @Override
   public List<User> queryUsers(List<Integer> ids) {
      QueryWrapper<User> query = new QueryWrapper<>();
      query.in("uid", ids);
      return userMapper.selectList(query);
   }

   @Override
   public UserWrapper.SaveDTO querySaveUserById(Integer id) {
      User user = userMapper.selectById(id);
      return DozerUtil.map(user, UserWrapper.SaveDTO.class);
   }

   @Override
   public User queryUserById(Integer id) {
      return userMapper.selectById(id);
   }

   @Override
   public Map<String, Integer> checkUsername(String username) {
      HashMap<String, Integer> map = new HashMap<>(1);
      map.put("bo", 1);
      QueryWrapper<User> query = new QueryWrapper<>();
      query.eq("uname", username);
      User user = userMapper.selectOne(query);
      if (user == null){
         map.put("bo", 0);
      }
      return map;
   }

   @Override
   public User saveUser(UserWrapper.SaveDTO saveDTO) {
      User user = DozerUtil.map(saveDTO, User.class);
      if (saveDTO.getUid() != null){
         user.setCapacity(userMapper.selectById(saveDTO.getUid()).getCapacity());
      }else {
         user.setCapacity(0);
      }
      user.setPassword(NormalUtils.encodePassword(saveDTO.getPassword()));
      user.setBrith(DateUtils.stringToSqlDate(saveDTO.getBrithDay()));
      if (saveDTO.getUid() == null){
         user.setCapacity(NormalConstant.ZERO);
         user.setHeadPic(NormalConstant.DEFAULT_HEAD_PIC);
         int insertId = userMapper.insert(user);
         if (insertId < 1){
            log.error("新增用户信息失败-信息为-{}", saveDTO.toString());
            return user;
         }
      }
      userMapper.updateById(user);
      return user;
   }

   @Override
   public LoginUserDTO signIn(LoginUserDTO loginUser) {
      QueryWrapper<User> query = new QueryWrapper<>();
      query.eq("uname", loginUser.getUname());
      User user = userMapper.selectOne(query);
      if (user == null){
         return null;
      }
      if (!NormalUtils.comparePassword(loginUser.getPassword(), user.getPassword())){
         return null;
      }
      return DozerUtil.map(user, LoginUserDTO.class);
   }

   @Override
   public Map<String, Integer> checkOldPassword(String username, String oldPassword) {
      HashMap<String, Integer> result = new HashMap<>(1);
      QueryWrapper<User> query = new QueryWrapper<>();
      query.eq("uname", username);
      result.put("bo", 0);
      User user = userMapper.selectOne(query);
      if (user != null && NormalUtils.comparePassword(oldPassword, user.getPassword())){
         result.put("bo", 1);
      }
      return result;
   }

   @Override
   public Map<Integer, String> getUidNameMap(List<Integer> userIdList) {
      if (CollectionUtils.isEmpty(userIdList)){
         return Collections.emptyMap();
      }
      QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
      userQueryWrapper.in("uid", userIdList);
      List<User> users = userMapper.selectList(userQueryWrapper);
      if (CollectionUtils.isEmpty(users)){
         return Collections.emptyMap();
      }
      return users.stream().collect(Collectors.toMap(User::getUid, User::getUname, (ok, nk) -> ok));
   }
}
