package com.chuyx.service;

import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Comments;
import com.chuyx.pojo.vo.CommentShowVO;
import com.chuyx.wrapper.CommentWrapper;

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

   /**
    * 新增最高级评论
    *
    * @param insertDTO 评论信息
    * @return 结果，0为失败
    */
   Integer saveComment(CommentWrapper.InsertDTO insertDTO);

   /**
    * 分页查询博客评论
    *
    * @param page 页面
    * @param blogId 博客id
    * @return 评论信息
    */
   List<CommentShowVO> queryPage(int page, int blogId);
}
