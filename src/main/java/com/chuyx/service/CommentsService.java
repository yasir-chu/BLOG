package com.chuyx.service;

import com.chuyx.entity.dto.CommentShowDTO;
import com.chuyx.entity.dto.Pager;
import com.chuyx.entity.po.Comments;
import java.util.List;

public interface CommentsService {
   int queryCountByBlogId(int id);

   boolean addComment(int blogId, int uid, String editorContent);

   Pager<CommentShowDTO> queryByBlogId(int blogId);

   Pager<CommentShowDTO> queryByBlogIdSmallPage(int blogId, int nowPage);

   int addChildComment(String targetUserName, int userId, String userParentName, String replyContent, int blogId, int parrentComId);

   int delComment(int id);

   int addChildComment(int userId, String userParentName, String replyContent, int blogId, int parrentComId);

   int getAllCommentsSize();

   List<Comments> getPageCommentsSize(int page, int size);

   int delCommentByBlogId(int id);
}
