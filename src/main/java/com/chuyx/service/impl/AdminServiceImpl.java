package com.chuyx.service.impl;

import com.alibaba.fastjson.JSON;
import com.chuyx.pojo.dto.AdminIndexMsgDTO;
import com.chuyx.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserService userService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private Jedis jedis;

    /**
     * redis key 一小时内有效的博客详情
     */
    private String ALL_SIZE_ABOUT_MY_BLOG = "redis.my.blog.all.size";

    /**
     * 一小时的秒
     */
    private Integer ONE_HOUR_SECOND = 60 * 60;

    @Override
    public AdminIndexMsgDTO toAdmin() {
        if (!jedis.exists(ALL_SIZE_ABOUT_MY_BLOG)) {
            Integer countUserSize = userService.getCountUserSize();
            Integer allBlogSize = blogService.getAllBlogSize();
            Integer allCommentsSize = commentsService.getAllCommentsSize();
            Integer countAuthorSize = userService.getCountAuthorSize();
            AdminIndexMsgDTO result = new AdminIndexMsgDTO(countUserSize, countAuthorSize, allBlogSize, allCommentsSize);
            jedis.setex(ALL_SIZE_ABOUT_MY_BLOG, ONE_HOUR_SECOND, JSON.toJSONString(result));
        }
        return JSON.parseObject(jedis.get(ALL_SIZE_ABOUT_MY_BLOG), AdminIndexMsgDTO.class);
    }
}
