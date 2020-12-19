package com.chuyx.service;

public interface EmailService {
    void sentEmailToMy(String content, int uid);

    void sentToUser(int uid);
}
