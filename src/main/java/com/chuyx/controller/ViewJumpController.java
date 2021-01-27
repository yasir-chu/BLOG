package com.chuyx.controller;

import com.chuyx.api.ViewJumpApi;
import com.chuyx.constant.NormalConstant;
import com.chuyx.pojo.dto.Pager;
import com.chuyx.pojo.vo.BlogBaseVO;
import com.chuyx.service.BlogService;
import com.chuyx.wrapper.BlogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Controller
public class ViewJumpController implements ViewJumpApi {

    @Autowired
    private BlogService blogService;

    @Override
    public String blogPage(Model model) {
        BlogWrapper.QueryPageDTO queryPageDTO = new BlogWrapper.QueryPageDTO();
        queryPageDTO.setSize(NormalConstant.TOP_SIZE);
        queryPageDTO.setPage(NormalConstant.ONE);
        Pager<BlogBaseVO> result = blogService.queryPageBlog(queryPageDTO);
        model.addAttribute("blogDTOS", result);
        return "ordinary/article";
    }
}
