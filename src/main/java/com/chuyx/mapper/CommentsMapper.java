package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.pojo.dto.CommentDTO;
import com.chuyx.pojo.model.Comments;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentsMapper extends BaseMapper<Comments> {
   int queryCountByBlogId(int id);

   boolean addCommnet(CommentDTO comments);

   List<Comments> queryByBlogId(int blogId);

   List<Comments> queryByBlogIdByPage(int blogId, int index, int size);

   List<Comments> queryByComId(int comId, int blogId);

   int addChildComment(Comments comments);

   int countSize(int blogId);

   int getAllCommentsSize();

   List<Comments> queryPageComment(int index, int size);

   int delComment(int id);

   int delCommentChilds(int id);

   int delCommentBuBlogId(int id);
}
