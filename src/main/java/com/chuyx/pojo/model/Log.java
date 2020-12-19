package com.chuyx.pojo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {

    @ApiModelProperty("主键id")
    private int id;

    @ApiModelProperty("用户id")
    private int uid;

    @ApiModelProperty("")
    private String event;

    @ApiModelProperty("日志内容")
    private String connent;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
