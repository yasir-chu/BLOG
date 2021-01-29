package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.CommentsMapper;
import com.chuyx.pojo.dto.CommentDTO;
import com.chuyx.pojo.dto.CommentShowDTO;
import com.chuyx.pojo.dto.CommentShowMsgDTO;
import com.chuyx.pojo.dto.LoginUserDTO;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Comments;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.CommentBaseVO;
import com.chuyx.pojo.vo.CommentShowVO;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.chuyx.utils.DozerUtil;
import com.chuyx.wrapper.CommentWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.xml.stream.events.Comment;

@Service
@Slf4j
public class CommentsServiceImpl implements CommentsService {
   @Autowired
   private CommentsMapper commentsMapper;
   @Autowired
   private UserService userService;

   @Override
   public int queryCountByBlogId(int id) {
      return this.commentsMapper.queryCountByBlogId(id);
   }

   @Override
   public boolean addComment(int blogId, int uid, String editorContent) {
      CommentDTO commentDTO = new CommentDTO();
      commentDTO.setBlogId(blogId);
      commentDTO.setContent(editorContent);
      commentDTO.setUid(uid);
      commentDTO.setParentId(0);
      return this.commentsMapper.addCommnet(commentDTO);
   }

   @Override
   public Pager<CommentShowDTO> queryByBlogId(int blogId) {
      Pager<CommentShowDTO> result = new Pager();
      List<Comments> comments = this.commentsMapper.queryByBlogId(blogId);
      List<CommentShowDTO> commentShowDTOS = new ArrayList();
      Iterator var5 = comments.iterator();

      while(var5.hasNext()) {
         Comments comment = (Comments)var5.next();
         CommentShowDTO commentShowDTO = new CommentShowDTO();
         CommentShowMsgDTO commentShowMsgDTO = this.toShow(comment);
         commentShowDTO.setParent(commentShowMsgDTO);
         List<Comments> childComments = this.commentsMapper.queryByComId(comment.getId(), blogId);
         ArrayList<CommentShowMsgDTO> childCommentShowMsgDTOS = new ArrayList();
         Iterator var11 = childComments.iterator();

         while(var11.hasNext()) {
            Comments childComment = (Comments)var11.next();
            CommentShowMsgDTO childCommentShowMsgDTO = this.toShow(childComment);
            childCommentShowMsgDTOS.add(childCommentShowMsgDTO);
         }

         commentShowDTO.setChilds(childCommentShowMsgDTOS);
         commentShowDTOS.add(commentShowDTO);
      }

      int countSize = this.commentsMapper.countSize(blogId);
      if (countSize < 5) {
         result.setSize(1);
      } else if (countSize / 5 > 0) {
         result.setSize(countSize / 5 + 1);
      } else {
         result.setSize(countSize / 5);
      }

      result.setTotal((long)countSize);
      result.setRows(commentShowDTOS);
      result.setCataId(0);
      result.setPage(1);
      return result;
   }

   @Override
   public Pager<CommentShowDTO> queryByBlogIdSmallPage(int blogId, int nowPage) {
      Pager<CommentShowDTO> result = new Pager();
      int index = (nowPage - 1) * 5;
      List<Comments> comments = this.commentsMapper.queryByBlogIdByPage(blogId, index, 5);
      List<CommentShowDTO> commentShowDTOS = new ArrayList();
      Iterator var7 = comments.iterator();

      while(var7.hasNext()) {
         Comments comment = (Comments)var7.next();
         CommentShowDTO commentShowDTO = new CommentShowDTO();
         CommentShowMsgDTO commentShowMsgDTO = this.toShow(comment);
         commentShowDTO.setParent(commentShowMsgDTO);
         List<Comments> childComments = this.commentsMapper.queryByComId(comment.getId(), blogId);
         ArrayList<CommentShowMsgDTO> childCommentShowMsgDTOS = new ArrayList();
         Iterator var13 = childComments.iterator();

         while(var13.hasNext()) {
            Comments childComment = (Comments)var13.next();
            CommentShowMsgDTO childCommentShowMsgDTO = this.toShow(childComment);
            childCommentShowMsgDTOS.add(childCommentShowMsgDTO);
         }

         commentShowDTO.setChilds(childCommentShowMsgDTOS);
         commentShowDTOS.add(commentShowDTO);
      }

      int countSize = this.commentsMapper.countSize(blogId);
      if (countSize < 5) {
         result.setSize(1);
      } else if (countSize / 5 > 0) {
         result.setSize(countSize / 5 + 1);
      } else {
         result.setSize(countSize / 5);
      }

      result.setTotal((long)countSize);
      result.setRows(commentShowDTOS);
      result.setCataId(0);
      result.setPage(nowPage);
      return result;
   }

   @Override
   public int addChildComment(String targetUserName, int userId, String userParentName, String replyContent, int blogId, int parrentComId) {
      Comments comments = new Comments();
      comments.setUid(userId);
      comments.setContent(replyContent);
      comments.setBlogId(blogId);
      comments.setAuthorOne(targetUserName);
      User user = this.userService.queryUserById(userId);
      comments.setAuthorTwe(user.getUname());
      comments.setParentId(parrentComId);
      this.userService.queryUserByUserName(userParentName);
      this.commentsMapper.addChildComment(comments);
      return 0;
   }

   @Override
   public int delComment(int id) {
      this.commentsMapper.delComment(id);
      this.commentsMapper.delCommentChilds(id);
      return 0;
   }

   @Override
   public int addChildComment(int userId, String userParentName, String replyContent, int blogId, int parrentComId) {
      Comments comments = new Comments();
      comments.setUid(userId);
      comments.setContent(replyContent);
      comments.setBlogId(blogId);
      User user = this.userService.queryUserById(userId);
      comments.setAuthorTwe(user.getUname());
      comments.setParentId(parrentComId);
      LoginUserDTO loginUserDTO = this.userService.queryUserByUserName(userParentName);
      comments.setParentId(loginUserDTO.getUid());
      this.commentsMapper.addChildComment(comments);
      return 0;
   }

   @Override
   public int getAllCommentsSize() {
      return this.commentsMapper.getAllCommentsSize();
   }

   @Override
   public List<Comments> getPageCommentsSize(int page, int size) {
      int index = (page - 1) * size;
      return this.commentsMapper.queryPageComment(index, size);
   }

   @Override
   public int delCommentByBlogId(int id) {
      return this.commentsMapper.delCommentBuBlogId(id);
   }

   @Override
   public Integer saveComment(CommentWrapper.InsertDTO insertDTO) {
      Comments insert = DozerUtil.map(insertDTO, Comments.class);
      insert.setParentId(0);
      int id = commentsMapper.insert(insert);
      if (id > 0){
         log.info("保存最大父评论成功-id为-{}", id);
         return id;
      }
      return 0;
   }

   @Override
   public List<CommentShowVO> queryPage(int page, int blogId) {
      QueryWrapper<Comments> query = new QueryWrapper<>();
      Page<Comments> pageMessage = new Page<>(Long.parseLong(String.valueOf(page)), NormalConstant.COMMENT_PAGE_SIZE);
      query.eq("blog_id", blogId);
      Page<Comments> comments = commentsMapper.selectPage(pageMessage, query);
      if (CollectionUtils.isEmpty(comments.getRecords())){
         return Collections.emptyList();
      }
      return toCommentVo(comments.getRecords());
   }

   /**
    * 将评论转换成展示数据
    * @param comments 原始评论
    * @return 转换后的展示数据
    */
   private List<CommentShowVO> toCommentVo(List<Comments> comments) {
      List<Integer> userIds = comments.stream().map(Comments::getUid).collect(Collectors.toList());
      List<User> users = userService.queryUsers(userIds);
      if (CollectionUtils.isEmpty(userIds)){
         return Collections.emptyList();
      }
      Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getUid, user -> user, (nk, ok) -> nk));
      List<CommentShowVO> result = new ArrayList<>(comments.size());
      comments.forEach((a) -> {
         CommentShowVO oneShowVO = new CommentShowVO();
         if (a.getParentId() == null || a.getParentId() == 0){
            CommentBaseVO temp = poToBaseVo(a, userMap);
         }

         result.add(oneShowVO);
      });
      return result;
   }

   /**
    * 将po类转成基础展示类
    * @param comment po类
    * @param userMap 用户信息map集合
    * @return 展示基础类
    */
   private CommentBaseVO poToBaseVo(Comments comment, Map<Integer, User> userMap) {
      CommentBaseVO temp = DozerUtil.map(comment, CommentBaseVO.class);
      if (!userMap.containsKey(comment.getUid()) && !userMap.containsKey(comment.getParentId())){
         return temp;
      }
      User user = userMap.get(comment.getUid());
      temp.setAuthor(user.getUname());
      temp.setOneHeadPic(user.getHeadPic());
      // TODO 没写完
      return temp;
   }

   public CommentShowMsgDTO toShow(Comments comment) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      CommentShowMsgDTO commentShowMsgDTO = new CommentShowMsgDTO();
      BeanUtils.copyProperties(comment, commentShowMsgDTO);
      User user = this.userService.queryUserById(comment.getUid());
      commentShowMsgDTO.setAuthor(user.getUname());
      commentShowMsgDTO.setTweHeadPic(user.getHeadPic());
      String format = simpleDateFormat.format(comment.getCreateTime());
      commentShowMsgDTO.setRepleaseDate(format);
      return commentShowMsgDTO;
   }
}
