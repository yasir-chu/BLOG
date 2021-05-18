package com.chuyx.service;

import com.chuyx.pojo.dto.*;
import com.chuyx.pojo.model.User;
import com.chuyx.wrapper.UserWrapper;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author cyx
 */
public interface UserService {

   /**
    * 博主申请
    * @param uid 用户id
    * @param logged 申请宣言
    */
   void applyBlogUpdate(Integer uid, String logged);

   /**
    * 获取用户总数
    * @return 用户总数
    */
   Integer getCountUserSize();

   /**
    * 获取所有博主总数
    * @return 博主总数
    */
   Integer getCountAuthorSize();

   /**
    * 获取正在等待审批成博主的用户
    * @param page 当前页
    * @param size 页面大小
    * @return 数据集
    */
   Pager<LoginUserDTO> getWaitAuthorPage(int page, int size);

   /**
    * 通过审核博主申请
    * @param uid 用户id
    * @return 更新数量
    */
   Integer passAuthor(int uid);

   /**
    * 分页获取用户
    * @param page 当前页
    * @param size 页面大小
    * @return 数据
    */
   Pager<AdminUser> getPageUser(int page, int size);

   /**
    * 删除用户
    * @param id 用户id
    * @return 删除数量
    */
   Integer delUser(Integer id);

   /**
    * 根据用户ids查找用户信息
    *
    * @param ids 用户ids
    * @return 用户信息
    */
   List<User> queryUsers(List<Integer> ids);

   /**
    * 根据用户id查找用户信息
    *
    * @param id 用户id
    * @return 用户信息
    */
   UserWrapper.SaveDTO querySaveUserById(Integer id);

   /**
    * 根据用户id查找用户信息
    *
    * @param id 用户id
    * @return 用户信息
    */
   User queryUserById(Integer id);

   /**
    * 检查用户名
    * @param username 用户名
    * @return 检查结果
    */
   Map<String, Integer> checkUsername(String username);

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

   /**
    * 修改用户时检查老密码是否正确
    * @param username 用户名
    * @param oldPassword 用户老密码
    * @return 检查结果
    */
   Map<String, Integer> checkOldPassword(String username, String oldPassword);

   /**
    * 根据用户id集合获取
    * @param userIdList 用户id集合
    * @return uid uname
    */
   Map<Integer, String> getUidNameMap(List<Integer> userIdList);


   /**
    * 查询用户
    * @param searchUserDTO 查询条件
    * @return 查询结果
    */
   Pager<AdminUser> searchUser(UserWrapper.SearchUserDTO searchUserDTO);
}
