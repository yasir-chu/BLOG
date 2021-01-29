package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.dto.RegisterDTO;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.CategoryService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.LoginService;
import com.chuyx.service.UserService;
import com.chuyx.utils.BlogUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chuyx.wrapper.BlogWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
   @Autowired
   LoginService loginService;
   @Autowired
   BlogService blogService;
   @Autowired
   CategoryService categoryService;
   @Autowired
   CommentsService commentsService;
   @Autowired
   UserService userService;

   // TODO 检查老密码的判断还没更新
   @RequestMapping(
      value = {"/checkOldPwd/{username}/{oldPwd}"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String checkOldPwd(@PathVariable("username") String username, @PathVariable("oldPwd") String oldPwd) {
      LoginUserDTO loginUserDTO = loginService.queryUserByName(username);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      Map<String, Integer> map = new HashMap();
      if (bCryptPasswordEncoder.matches(oldPwd, loginUserDTO.getPassword())) {
         map.put("bo", 0);
      } else {
         map.put("bo", 1);
      }
      String s = JSON.toJSONString(map);
      return s;
   }
}
