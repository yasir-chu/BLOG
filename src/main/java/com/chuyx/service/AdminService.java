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

   /**
    * 获取博客整体情况
    * @return 个内容数据
    */
   AdminIndexMsgDTO toAdmin();

}
