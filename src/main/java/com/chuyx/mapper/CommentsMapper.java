package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.pojo.dto.CommentDTO;
import com.chuyx.pojo.model.Comments;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author cyx
 */
@Mapper
@Repository
public interface CommentsMapper extends BaseMapper<Comments> {

}
