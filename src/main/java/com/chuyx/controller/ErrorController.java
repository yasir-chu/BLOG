package com.chuyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/error"})
public class ErrorController {
    @GetMapping({"/404"})
    public String error_404() {
        return "comm/error_404";
    }

    @GetMapping({"/500"})
    public String error_500() {
        return "comm/error_500";
    }
}
