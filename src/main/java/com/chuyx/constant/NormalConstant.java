package com.chuyx.constant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Comments;
import com.chuyx.pojo.vo.CommentShowVO;

import java.util.Collections;

/**
 * 常量
 *
 * @author yasir.chu
 */
public class NormalConstant {
   /**
    * 发送邮箱号码
    */
   public static String MY_FAIL = "cyx_serendipity@126.com";

   /**
    * 七牛云的AccessKey
    */
   public static String QI_NIU_ACCESS_KEY = "5ngLMovI40oojUYkdSvnORe0JtpY4yH3zjpB7yjr";

   /**
    * 七牛云的secretKey
    */
   public static String QI_NIE_SECRET_KEY = "FvDyWzPUobu5ctFofYFfyah91vAXcOm7CBv1nbhz";

   /**
    * 热门博客和最新博客数量
    */
   public static Integer TOP_SIZE = 10;

   /**
    * 页面大小
    */
   public static Integer PAGE_SIZE = 5;

   /**
    * 常量2
    */
   public static Integer TWE = 2;


   /**
    * 常量1
    */
   public static Integer ONE = 1;

   /**
    * 常量0
    */
   public static Integer ZERO = 0;

   /**
    * 常量-1
    */
   public static Integer NEGATIVE_ONE = -1;

   /**
    * 第一页， 10个
    */
   public static Page<Blog> RANKING_PAGE = new Page<>(NormalConstant.ONE,NormalConstant.TOP_SIZE);

   /**
    * long 5
    */
   public static long COMMENT_PAGE_SIZE = 5L;

   /**
    * 空页
    */
   public static Pager<CommentShowVO> COMMENT_NULL_PAGER = new Pager<>(0, 0, Collections.emptyList(), 0L, 0);

   /**
    * 默认头像
    */
   public static String DEFAULT_HEAD_PIC = "http://img.chuyx.top/FtijrH85AbdHe1rPpIuAxgA6u5wD";

   /**
    * 负一
    */
   public static Integer DOWN_ONE = -1;

   /**
    * 错误码
    */
   public static Integer ERROR_CODE = 500;
}
