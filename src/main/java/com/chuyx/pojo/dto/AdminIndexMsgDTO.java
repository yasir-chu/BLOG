package com.chuyx.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminIndexMsgDTO implements Serializable {

    private int userSize;

    private int authorSize;

    private int blogSize;

    private int commentsSize;
}
