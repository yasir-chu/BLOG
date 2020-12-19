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
public class AdminUser implements Serializable {

    private int uid;

    private String uname;

    private int capacity;

    private String email;

    private String brith;


}
