package com.chuyx.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chuyx.utils.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class File {
    @RequestMapping(value = {"/file/upload"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String fileUpload(@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request) throws IOException, JSONException {
        String filename = "chuyx-" + UUID.randomUUID().toString().replaceAll("-", "");
        String s = UploadUtil.uploadQiniu(file);
        JSONObject res = new JSONObject();
        res.put("url", "http://img.chuyx.top/" + s);
        res.put("success", 1);
        res.put("message", "upload success!");
        return res.toString();
    }

    @RequestMapping(value = {"/uploader/headpic"}, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String uploaderHeadpic(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles("files");
        MultipartFile file = (MultipartFile) files.get(0);
        String filename = "chuyx-" + UUID.randomUUID().toString().replaceAll("-", "");
        String url = "http://img.chuyx.top/" + UploadUtil.uploadQiniu(file);
        HttpSession session = request.getSession();
        return url;
    }
}
