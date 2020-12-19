package com.chuyx.utils;

import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.model.Blog;
import java.text.SimpleDateFormat;

public class BlogUtils {
   public static BlogDTO BolgDateToYMD(Blog blog, BlogDTO blogDTO) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
      String resleaseTime = simpleDateFormat.format(blog.getReleaseDate());
      String year = resleaseTime.substring(0, 4);
      blogDTO.setYear(year);
      String month = resleaseTime.substring(5, 7);
      blogDTO.setMonth(month);
      String day = resleaseTime.substring(8, 10);
      blogDTO.setDay(day);
      return blogDTO;
   }
}
