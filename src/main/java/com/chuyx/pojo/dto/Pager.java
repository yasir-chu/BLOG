package com.chuyx.pojo.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yasir.chu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pager<T> implements Serializable {

   @ApiModelProperty(notes = "当前页")
   private Integer page;

   @ApiModelProperty(notes = "总页数")
   private Integer size;

   @ApiModelProperty(notes = "数据集")
   private List<T> rows;

   @ApiModelProperty(notes = "总数")
   private long total;

   @ApiModelProperty(notes = "类别")
   private Integer cataId;
}
