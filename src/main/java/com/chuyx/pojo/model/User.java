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
public class User implements Serializable {

    @ApiModelProperty("主键id")
    private int uid;

    @ApiModelProperty("用户名")
    private String uname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("生日")
    private Date brith;

    @ApiModelProperty("性别")
    private int sex;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("身份")
    private int capacity;

    @ApiModelProperty("签名")
    private String logged;

    @ApiModelProperty("头像路径")
    private String headPic;
}

