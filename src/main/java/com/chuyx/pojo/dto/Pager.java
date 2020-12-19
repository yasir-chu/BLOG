package com.chuyx.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yasir.chu
 */
@Data
public class Pager<T> implements Serializable {

    private int page;

    private int size;

    private List<T> rows;

    private long total;

    private int cataId;
}
