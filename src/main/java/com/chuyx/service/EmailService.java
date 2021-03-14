package com.chuyx.service;

/**
 * @author yasir.chu
 */
public interface EmailService {

   /**
    * 发送邮件给自己
    *
    * @param content 发送内容
    * @param uid 用户id
    */
   void sentEmailToMy(String content, int uid);

   /**
    * 发送通过审核邮寄给用户
    * @param uid 用户id
    */
   void sentToUser(int uid);
}
