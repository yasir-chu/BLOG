package com.chuyx.pojo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log implements Serializable {

   private int id;

   private int uid;

   private String event;

   private String connent;

   private Date createTime;
}
