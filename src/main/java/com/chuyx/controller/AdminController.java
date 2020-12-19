package com.chuyx.controller;

import com.alibaba.fastjson.JSON;
import com.chuyx.pojo.dto.*;
import com.chuyx.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping({"/"})
    public String toAdmin(Model model) {
        AdminIndexMsgDTO result = this.adminService.toAdmin();
        model.addAttribute("adminMsg", result);
        return "admin/admin";
    }

    @RequestMapping({"/toblog"})
    public String toblog() {
        return "admin/blogAdmin";
    }

    @ApiOperation(value = "分页获取博客")
    @PostMapping(value = {"/blog"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String blog(Model model) {
        Pager<BlogDTO> blog = adminService.blog();
        return JSON.toJSONString(blog);
    }

    @PostMapping(
            value = {"/delBlog"},
            produces = {"application/json;charset=utf-8"}
    )
    @ResponseBody
    public String delBlog(int id, Model model) {
        this.adminService.delBlog(id);
        Pager<BlogDTO> blog = this.adminService.blog();
        return JSON.toJSONString(blog);
    }

    @PostMapping(
            value = {"/page/{page}"},
            produces = {"application/json;charset=utf-8"}
    )
    @ResponseBody
    public String blogPage(@PathVariable("page") int page, Model model) {
        Pager<BlogDTO> blogDTOPager = this.adminService.adminBlogPage(page);
        return JSON.toJSONString(blogDTOPager);
    }

    @RequestMapping({"/tocheckAuthor"})
    public String toCheckAuthor() {
        return "admin/checkAdmin";
    }

    @PostMapping(
            value = {"/allWaitPassAuthor"},
            produces = {"application/json;charset=utf-8"}
    )
    @ResponseBody
    public String passAuthor() {
        Pager<LoginUserDTO> pager = this.adminService.allWaitPassAuthor(1, 10);
        return JSON.toJSONString(pager);
    }

    @PostMapping(value = {"/allWaitPassAuthor/{page}"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String passAuthor(@PathVariable("page") int page) {
        Pager<LoginUserDTO> pager = this.adminService.allWaitPassAuthor(page, 10);
        return JSON.toJSONString(pager);
    }

    @PostMapping(value = {"/passAuthor"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String passAuthor(Model model, int id) {
        this.adminService.passAuthor(id);
        Pager<LoginUserDTO> pager = this.adminService.allWaitPassAuthor(1, 10);
        return JSON.toJSONString(pager);
    }

    @RequestMapping({"/tocommentAdmin"})
    public String toCoommentAdmin() {
        return "admin/commentAdmin";
    }

    @PostMapping(value = {"/allComments"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String allComments() {
        Pager<AdminComments> allCommentsPage = this.adminService.getAllCommentsPage(1, 10);
        return JSON.toJSONString(allCommentsPage);
    }

    @RequestMapping(
            value = {"/allComments/{page}"},
            produces = {"application/json;charset=utf-8"}
    )
    @ResponseBody
    public String allComments(@PathVariable("page") int page) {
        Pager<AdminComments> allCommentsPage = this.adminService.getAllCommentsPage(page, 10);
        return JSON.toJSONString(allCommentsPage);
    }

    @RequestMapping(value = {"/delComments"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String delComments(int id) {
        this.adminService.delComment(id);
        Pager<AdminComments> allCommentsPage = this.adminService.getAllCommentsPage(1, 10);
        return JSON.toJSONString(allCommentsPage);
    }

    @RequestMapping({"/toUserAdmin"})
    public String toUserAdmin() {
        return "admin/userAdmin";
    }

    @RequestMapping(value = {"/allUser"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String allUser() {
        Pager<AdminUser> allCommentsPage = this.adminService.getAllUserPage(1, 10);
        return JSON.toJSONString(allCommentsPage);
    }

    @RequestMapping(value = {"/allUser/{page}"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String allUserPage(@PathVariable("page") int page) {
        Pager<AdminUser> allCommentsPage = this.adminService.getAllUserPage(page, 10);
        return JSON.toJSONString(allCommentsPage);
    }

    @RequestMapping(value = {"/delUser"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String delUser(int id) {
        this.adminService.delUser(id);
        Pager<AdminUser> allCommentsPage = this.adminService.getAllUserPage(1, 10);
        return JSON.toJSONString(allCommentsPage);
    }
}
