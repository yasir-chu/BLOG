package com.chuyx.utils;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.model.Blog;

import java.text.SimpleDateFormat;

/**
 * 博客工具类
 *
 * @author yasir.chu
 */
public class BlogUtils {

    /**
     * 时间修正
     *
     * @param blog    博客PO
     * @param blogDTO 博客数据传输对象
     * @return 时间修正后的博客传输对象
     */
    public static BlogDTO BolgDateToYMD(Blog blog, BlogDTO blogDTO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String releaseTime = simpleDateFormat.format(blog.getReleaseDate());
        String year = releaseTime.substring(0, 4);
        blogDTO.setYear(year);
        String month = releaseTime.substring(5, 7);
        blogDTO.setMonth(month);
        String day = releaseTime.substring(8, 10);
        blogDTO.setDay(day);
        return blogDTO;
    }
}
