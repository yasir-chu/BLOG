package com.chuyx.service;

import com.chuyx.pojo.vo.BlogBaseVO;

/**
 * @author yasir.chu
 * date 2021/1/28
 **/
public interface ViewJumpService {

    /**
     * 阅读博客后台所需
     *
     * @param id 博客id
     * @return 博客视图数据类
     */
    BlogBaseVO readBlog(int id);
}
