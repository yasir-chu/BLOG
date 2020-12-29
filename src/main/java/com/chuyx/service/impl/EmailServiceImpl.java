package com.chuyx.service.impl;

import com.chuyx.entity.po.User;
import com.chuyx.service.EmailService;
import com.chuyx.service.UserService;
import com.chuyx.constant.NormalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
   @Autowired
   JavaMailSenderImpl mailSender;
   @Autowired
   UserService userService;

   @Override
   @Async
   public void sentEmailToMy(String content, int uid) {
      SimpleMailMessage message = new SimpleMailMessage();
      User user = this.userService.queryUserById(uid);
      message.setSubject("博主申请通知");
      message.setText("用户-->" + user.getUname() + "（" + uid + ")，向您申请了博主资格，申请内容如下：\n" + content);
      message.setTo(NormalConstant.MY_FAIL);
      message.setFrom(NormalConstant.MY_FAIL);
      this.mailSender.send(message);
   }

   @Override
   @Async
   public void sentToUser(int uid) {
      User user = this.userService.queryUserById(uid);
      String targetEmail = user.getEmail();
      SimpleMailMessage message = new SimpleMailMessage();
      message.setSubject("ChuyxBlog博主申请通过通知");
      message.setText("亲爱的" + user.getUname() + ":\n\t您通过了站长的申请，成为了一名ChuyxBlog中的博主，期待您优秀的技术分享。感谢您的参与");
      message.setTo(targetEmail);
      message.setFrom(NormalConstant.MY_FAIL);
      this.mailSender.send(message);
   }
}
