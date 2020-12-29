package com.chuyx.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yasir.chu
 * @date 2020/12/22
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("")
    private Integer name;
}
