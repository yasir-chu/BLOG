package com.chuyx.service;

import com.chuyx.entity.dto.AdminComments;
import com.chuyx.entity.dto.AdminIndexMsgDTO;
import com.chuyx.entity.dto.AdminUser;
import com.chuyx.entity.dto.BlogDTO;
import com.chuyx.entity.dto.LoginUserDTO;
import com.chuyx.entity.dto.Pager;

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
