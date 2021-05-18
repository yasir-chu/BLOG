package com.chuyx.wrapper;

import com.sun.istack.internal.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chuyx
 * @data 2021-01-29
 */
public class UserWrapper {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "UserWrapper.SaveDTO")
    public static class SaveDTO {

        @ApiModelProperty(notes = "用户id 有就更新 没有新增")
        private Integer uid;

        @NotNull
        @ApiModelProperty(notes = "用户名")
        private String uname;

        @NotNull
        @ApiModelProperty(notes = "电话")
        private String phone;

        @NotNull
        @ApiModelProperty(notes = "生日")
        private String brithDay;

        @NotNull
        @ApiModelProperty(notes = "邮箱")
        private String email;

        @NotNull
        @ApiModelProperty(notes = "性别")
        private Integer sex;

        @NotNull
        @ApiModelProperty(notes = "密码")
        private String password;

        @ApiModelProperty(notes = "二次确认密码")
        private String repeatPassword;

        @ApiModelProperty(notes = "用户身份 0 普通用户 1是博主 2是管理员 -1 是申请了博主未通过")
        private String capacity;

        @NotNull
        @ApiModelProperty(notes = "头像路径")
        private String headPic;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(value = "UserWrapper.SearchUserDTO")
    public static class SearchUserDTO {

        @ApiModelProperty(notes = "模糊搜索用户名")
        private String userName;

        @ApiModelProperty(notes = "级别")
        private Integer capacity;

    }
}
