package com.chuyx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuyx.entity.po.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper extends BaseMapper<Log> {
   int inserntLog(Log log);
}
