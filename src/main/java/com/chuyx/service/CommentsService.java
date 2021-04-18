package com.chuyx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.pojo.dto.AdminComments;
import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Comments;
import com.chuyx.pojo.vo.CommentBaseVO;
import com.chuyx.pojo.vo.CommentShowVO;
import com.chuyx.wrapper.CommentWrapper;

import java.util.List;
import java.util.Map;

public interface CommentsService {
   int queryCountByBlogId(int id);

   /*
   TODO del
    */
   int addChildComment(String targetUserName, int userId, String userParentName, String replyContent, int blogId, int parentComId);

   /**
    * 删除评论
    * @param id 评论id
    * @return 删除数量
    */
   int delComment(int id);

   /**
    * 新增子评论
    * @param userId 用户id
    * @param userParentName 父评论用户名
    * @param replyContent 评论内容
    * @param blogId 博客id
    * @param parentComId 父评论id
    */
   void addChildComment(int userId, String userParentName, String replyContent, int blogId, int parentComId);

   /**
    * 获取所有评论总数
    * @return 评论总数
    */
   Integer getAllCommentsSize();

   /**
    * 评论管理 分页获取评论
    * @param page 当前页
    * @param size 页面大小
    * @return 数据集
    */
   Pager<AdminComments> getPageCommentsSize(Integer page, Integer size);

   /**
    * 删除博客下的所有评论
    * @param blogId 博客id
    * @return 删除数量
    */
   Integer delCommentByBlogId(Integer blogId);

   /**
    * 新增最高级评论
    *
    * @param insertDTO 评论信息
    * @return 结果，0为失败
    */
   CommentBaseVO saveComment(CommentWrapper.InsertDTO insertDTO);

   /**
    * 分页查询博客评论
    *
    * @param page 页面
    * @param blogId 博客id
    * @return 评论信息
    */
   Pager<CommentShowVO> queryPage(int page, int blogId);

   /**
    * 根据博客id集合获取评论总数
    * @param blogIdList 博客id集合
    * @return 博客id 评论总数
    */
    Map<Integer, Long> getBlogIdCommentsMap(List<Integer> blogIdList);
}
