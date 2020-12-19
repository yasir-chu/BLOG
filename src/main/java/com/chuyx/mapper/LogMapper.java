package com.chuyx.mapper;

import com.chuyx.pojo.po.Log;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LogMapper {
    int inserntLog(Log log);
}
