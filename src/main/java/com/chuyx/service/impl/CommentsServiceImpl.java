package com.chuyx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuyx.constant.NormalConstant;
import com.chuyx.mapper.CommentsMapper;
import com.chuyx.pojo.dto.*;
import com.chuyx.pojo.model.Blog;
import com.chuyx.pojo.model.Category;
import com.chuyx.pojo.model.Comments;
import com.chuyx.pojo.model.User;
import com.chuyx.pojo.vo.CommentBaseVO;
import com.chuyx.pojo.vo.CommentShowVO;
import com.chuyx.service.BlogService;
import com.chuyx.service.CommentsService;
import com.chuyx.service.UserService;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import com.chuyx.utils.DateUtils;
import com.chuyx.utils.DozerUtil;
import com.chuyx.utils.NormalUtils;
import com.chuyx.wrapper.CommentWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.security.pkcs11.wrapper.Constants;

import javax.xml.stream.events.Comment;

/**
 * @author cyx
 */
@Service
@Slf4j
public class CommentsServiceImpl implements CommentsService {

   @Autowired
   private CommentsMapper commentsMapper;

   @Autowired
   private UserService userService;

   @Autowired
   private BlogService blogService;

   @Override
   public int queryCountByBlogId(int id) {
      if (id <= 0){
         return NormalConstant.ZERO;
      }
      QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("blog_id", id);
      return commentsMapper.selectCount(queryWrapper);
   }

   @Override
   public int addChildComment(String targetUserName, int userId, String userParentName, String replyContent, int blogId, int parrentComId) {
//      Comments comments = new Comments();
//      comments.setUid(userId);
//      comments.setContent(replyContent);
//      comments.setBlogId(blogId);
//      comments.setAuthorOne(targetUserName);
//      User user = this.userService.queryUserById(userId);
//      comments.setAuthorTwe(user.getUname());
//      comments.setParentId(parrentComId);
//      this.userService.queryUserByUserName(userParentName);
//      this.commentsMapper.addChildComment(comments);
      return 0;
   }

   @Override
   public int delComment(int id) {
      commentsMapper.deleteById(id);
      QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("parent_id", id);
      return commentsMapper.delete(queryWrapper);
   }

   @Override
   public void addChildComment(int userId, String userParentName, String replyContent, int blogId, int parentComId) {
      Comments comments = new Comments();
      comments.setUid(userId);
      comments.setContent(replyContent);
      comments.setBlogId(blogId);
      User user = userService.queryUserById(userId);
      comments.setAuthorTwe(user.getUname());
      comments.setParentId(parentComId);
      comments.setAuthorOne(userParentName);
      commentsMapper.insert(comments);
   }

   @Override
   public Integer getAllCommentsSize() {
      return commentsMapper.selectCount(new QueryWrapper<>());
   }

   @Override
   public Pager<AdminComments> getPageCommentsSize(Integer page, Integer size) {
      Page<Comments> commentPage = new Page<>(page, size);
      Page<Comments> commentsPage = commentsMapper.selectPage(commentPage, null);
      return NormalUtils.pagerRows(commentPage, mapListAdminComments(commentsPage.getRecords()));
   }

   /**
    * 数据转换
    * @param records 待转换数据
    * @return 转换好后数据集
    */
   private List<AdminComments> mapListAdminComments(List<Comments> records) {
      if (CollectionUtils.isEmpty(records)){
         return Collections.emptyList();
      }
      List<Integer> userIdList = records.stream().map(Comments::getUid).distinct().collect(Collectors.toList());
      Map<Integer, String> uidNameMap = userService.getUidNameMap(userIdList);
      List<Integer> blogIdList = records.stream().map(Comments::getBlogId).distinct().collect(Collectors.toList());
      Map<Integer, String> blogNameMap = blogService.getBlogIdNameMap(blogIdList);
      return records.stream().map(a -> {
         AdminComments item = DozerUtil.map(a, AdminComments.class);
         if (uidNameMap.containsKey(a.getUid())){
            item.setAuthor(uidNameMap.get(a.getUid()));
         }
         if(blogNameMap.containsKey(a.getBlogId())){
            item.setBlogName(blogNameMap.get(a.getBlogId()));
         }
         item.setTime(DateUtils.dateToString(a.getCreateTime()));
         return item;
      }).collect(Collectors.toList());
   }

   @Override
   public Integer delCommentByBlogId(Integer id) {
      if (id == null || id < 0){
         return 0;
      }
      QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
      queryWrapper.eq("blog_id", id);
      return commentsMapper.delete(queryWrapper);
   }

   @Override
   public CommentBaseVO saveComment(CommentWrapper.InsertDTO insertDTO) {
      Comments insert = DozerUtil.map(insertDTO, Comments.class);
      insert.setCreateTime(DateUtils.getSqlNowDate());
      User parentUser = null;
      User nowUser = userService.queryUserById(insertDTO.getUid());
      int id = 0;
      if (insertDTO.getParentId() == 0) {
         id = commentsMapper.insert(insert);
      }
      if (insertDTO.getParentId() > 0) {
         Comments comments = commentsMapper.selectById(insert.getParentId());
         parentUser = userService.queryUserById(comments.getUid());
         insert.setAuthorTwe(nowUser.getUname());
         insert.setAuthorOne(parentUser.getUname());
         id = commentsMapper.insert(insert);
      }
      if (id > 0) {
         log.info("保存评论成功-保存数量为：{}", id);
      }
      return poToBaseVoAtAddComment(insert, nowUser, parentUser);
   }

   @Override
   public Pager<CommentShowVO> queryPage(int page, int blogId) {
      QueryWrapper<Comments> query = new QueryWrapper<>();
      Page<Comments> pageMessage = new Page<>(Long.parseLong(String.valueOf(page)), NormalConstant.COMMENT_PAGE_SIZE);
      query.eq("blog_id", blogId);
      query.eq("parent_id", NormalConstant.ZERO);
      query.orderByDesc("create_time");
      IPage<Comments> comments = commentsMapper.selectPage(pageMessage, query);
      List<Integer> listIds = comments.getRecords().stream().map(Comments::getId).collect(Collectors.toList());
      QueryWrapper<Comments> queryResult = new QueryWrapper<>();
      queryResult.in("parent_id", listIds);
      List<Comments> commentResult = commentsMapper.selectList(queryResult);
      if (CollectionUtils.isEmpty(comments.getRecords())){
         return NormalConstant.COMMENT_NULL_PAGER;
      }
      commentResult.addAll(comments.getRecords());
      List<CommentShowVO> rows = toCommentVo(commentResult);
      return NormalUtils.pagerRows(comments, rows);
   }

   @Override
   public Map<Integer, Long> getBlogIdCommentsMap(List<Integer> blogIdList) {
      if (CollectionUtils.isEmpty(blogIdList)){
         return Collections.emptyMap();
      }
      QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
      queryWrapper.in("blog_id", blogIdList);
      List<Comments> comments = commentsMapper.selectList(queryWrapper);
      if (CollectionUtils.isEmpty(comments)){
         return Collections.emptyMap();
      }
      return comments.stream().collect(Collectors.groupingBy(Comments::getBlogId, Collectors.counting()));
   }

   /**
    * 将新增评论转换成展示数据
    * @param comments 原始评论
    * @param nowUser 该评论的用户信息
    * @param parentUser 父评论的用户信息
    * @return 转换后的展示数据
    */
   private CommentBaseVO poToBaseVoAtAddComment(Comments comments, User nowUser, User parentUser) {
      CommentBaseVO result = DozerUtil.map(comments, CommentBaseVO.class);
      result.setAuthorTwe(nowUser.getUname());
      result.setAuthor(nowUser.getUname());
      result.setTweHeadPic(nowUser.getHeadPic());
      result.setReleaseDate(DateUtils.getDateNowString());
      if (parentUser != null) {
         result.setOneHeadPic(parentUser.getHeadPic());
         result.setAuthorOne(nowUser.getUname());
      }
      return result;
   }

   /**
    * 将评论转换成展示数据
    * @param comments 原始评论
    * @return 转换后的展示数据
    */
   private List<CommentShowVO> toCommentVo(List<Comments> comments) {
      List<Integer> userIds = comments.stream().map(Comments::getUid).collect(Collectors.toList());
      userIds.addAll(comments.stream().map(Comments::getUid).collect(Collectors.toList()));
      List<User> users = userService.queryUsers(userIds.stream().distinct().filter((a) -> !a.equals(NormalConstant.ZERO)).collect(Collectors.toList()));
      if (CollectionUtils.isEmpty(userIds)){
         return Collections.emptyList();
      }
      Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(User::getUid, user -> user, (nk, ok) -> nk));
      List<CommentShowVO> result = new ArrayList<>(comments.size());
      Map<Integer, List<CommentBaseVO>> commentMap = getCommentMap(comments, userMap);
      List<CommentBaseVO> commentBaseVos = commentMap.get(0);
      commentBaseVos.forEach((a) -> {
         CommentShowVO temp = new CommentShowVO();
         temp.setParent(a);
         temp.setChildren(commentMap.get(a.getId()));
         result.add(temp);
      });
      return result;
   }

   /**
    * 将博客下所有评论以父评论id为key，以基础视图类为value，分好
    * @param comments 所有评论
    * @param userMap 用户信息
    * @return 结果
    */
   private Map<Integer, List<CommentBaseVO>> getCommentMap(List<Comments> comments, Map<Integer, User> userMap) {
      Map<Integer, Comments> collect = comments.stream().collect(Collectors.toMap(Comments::getId, (comment) -> comment, (ok, nk) -> nk));
      return comments.stream().map((a) -> {
         if (a.getParentId() != 0){
            User parentUser = userMap.get(collect.get(a.getParentId()).getUid());
            return poToBaseVo(a, userMap, parentUser);
         }
         return poToBaseVo(a, userMap, null);
      }).collect(Collectors.groupingBy(CommentBaseVO::getParentId));
   }

   /**
    * 将po类转成基础展示类
    * @param comment po类
    * @param userMap 用户信息map集合
    * @param parentUser 父评论的用户信息
    * @return 展示基础类
    */
   private CommentBaseVO poToBaseVo(Comments comment, Map<Integer, User> userMap, User parentUser) {
      CommentBaseVO temp = DozerUtil.map(comment, CommentBaseVO.class);
      if (!userMap.containsKey(comment.getUid()) && !userMap.containsKey(comment.getParentId())){
         return temp;
      }
      User user = userMap.get(comment.getUid());
      temp.setAuthor(user.getUname());
      temp.setAuthorTwe(user.getUname());
      temp.setTweHeadPic(user.getHeadPic());
      temp.setReleaseDate(DateUtils.dateToString(comment.getCreateTime()));
      if (comment.getParentId() == 0){
         return temp;
      }
      if (parentUser == null){
         return null;
      }
      temp.setOneHeadPic(parentUser.getHeadPic());
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
