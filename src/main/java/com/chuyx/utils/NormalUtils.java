package com.chuyx.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chuyx.pojo.dto.Pager;

import java.util.List;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/

public class NormalUtils {

    /**
     * 将MP的iPage转变成我们自己的pager类
     *
     * @param iPage MP的分页信息
     * @param <T>   分页信息的数据类
     * @return pager的分页信息
     */
    public static <T> Pager<T> pagerTransformation(IPage<T> iPage) {
        Pager<T> result = new Pager<>();
        if (iPage.getSize() > 0) {
            result.setSize(Integer.parseInt(String.valueOf(iPage.getSize())));
        }
        result.setTotal(iPage.getTotal());
        result.setRows(iPage.getRecords());
        result.setPage(Integer.parseInt(String.valueOf(iPage.getPages())));
        return result;
    }

    /**
     * 设置页面数据
     *
     * @param iPage MP的分页信息
     * @param rows  需要的数据
     * @param <T>   分页信息的数据类
     * @param <V>   需要的数据的类
     * @return pager的分页信息
     */
    public static <T, V> Pager<V> pagerRows(IPage<T> iPage, List<V> rows) {
        Pager<V> vPager = new Pager<V>();
        if (iPage.getSize() > 0) {
            vPager.setSize(Integer.parseInt(String.valueOf(iPage.getSize())));
        }
        vPager.setTotal(iPage.getTotal());
        vPager.setRows(rows);
        vPager.setPage(Integer.parseInt(String.valueOf(iPage.getPages())));
        return vPager;
    }
}
