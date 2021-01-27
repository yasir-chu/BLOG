package com.chuyx.controller;

import com.chuyx.api.ViewJumpApi;
import org.springframework.stereotype.Controller;

/**
 * @author yasir.chu
 * @date 2021/1/27
 **/
@Controller
public class ViewJumpController implements ViewJumpApi {

    @Override
    public String blogPage() {
        return "ordinary/article";
    }
}
