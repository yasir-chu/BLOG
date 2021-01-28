package com.chuyx.service.impl;

import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.ViewJumpService;
import com.chuyx.utils.DozerUtil;
import com.chuyx.wrapper.BlogWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yasir.chu
 * @date 2021/1/28
 **/
@Service
@Slf4j
public class ViewJumpServiceImpl implements ViewJumpService {

    @Autowired
    private BlogService blogService;

    @Override
    public BlogBaseVO readBlog(int id) {
        BlogBaseVO blog = blogService.queryBlogById(id);
        if (blog == null){
            log.warn("没有这个博客信息-id为-{}", id);
            return null;
        }
        updateVisitCount(blog);
        return blogService.queryBlogById(id);
    }


    /**
     * 增加博客访问量
     *
     * @param blog 博客信息
     */
    private void updateVisitCount(BlogBaseVO blog) {
        Integer visitCount = blog.getVisitCount();
        visitCount += 1;
        Integer save = blogService.updateBlogVisitCount(visitCount, blog.getId());
        if (save < 1){
            log.info("更新博客访问量失败");
        }
        log.info("更新博客访问量成功-博客id为{}-更新前访问量为{}-更新后访问量为{}", blog.getId(), visitCount, blog.getVisitCount());
    }
}
