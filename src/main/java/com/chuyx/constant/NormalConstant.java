package com.chuyx.constant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.pojo.model.Blog;

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
    * 常量1
    */
   public static Integer ONE = 1;

   public static Page<Blog> RANKING_PAGE = new Page<>(NormalConstant.ONE,NormalConstant.TOP_SIZE);
}
