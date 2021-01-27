package com.chuyx.service;

import com.chuyx.pojo.dto.AdminComments;
import com.chuyx.pojo.dto.AdminIndexMsgDTO;
import com.chuyx.pojo.dto.AdminUser;
import com.chuyx.pojo.dto.BlogDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;

/**
 * @author yasir.chu
 */
public interface AdminService {
   AdminIndexMsgDTO toAdmin();

   Pager<BlogDTO> blog();

   int delBlog(int id);

   Pager<BlogDTO> adminBlogPage(int page);

   Pager<LoginUserDTO> allWaitPassAuthor(int page, int size);

   int passAuthor(int uid);

   Pager<AdminComments> getAllCommentsPage(int page, int size);

   int delComment(int id);

   Pager<AdminUser> getAllUserPage(int page, int size);

   int delUser(int id);
}
