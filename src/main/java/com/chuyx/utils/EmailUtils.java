package com.chuyx.utils;

import com.chuyx.constant.NormalConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * @author yasir.chu
 * @date 2021/2/5
 **/
@Component
public class EmailUtils {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 邮件发送
     *
     * @param comment 发送内容
     * @param targetMail 目标邮件
     */
    public void sentMail(String comment, String targetMail){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("博主申请通知");
        message.setText(comment);
        message.setTo(targetMail);
        message.setFrom(NormalConstant.MY_FAIL);
        mailSender.send(message);
    }
}
