package com.chuyx.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuyx
 * @data 2021-04-19
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorVO {

    @ApiModelProperty(notes = "状态码")
    private Integer code;

    @ApiModelProperty(notes = "原因")
    private String reason;
}
