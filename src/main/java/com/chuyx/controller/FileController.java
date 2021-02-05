package com.chuyx.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chuyx.utils.UploadUtil;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@Slf4j
public class File {

   @RequestMapping(value = {"/file/upload"}, produces = {"application/json;charset=utf-8"})
   @ResponseBody
   public String fileUpload(@RequestParam(value = "editormd-image-file",required = true) MultipartFile file, HttpServletRequest request) throws IOException, JSONException {
      String filename = UploadUtil.uploadQiniu(file);
      if (StringUtils.isEmpty(filename)){
         log.error("七牛云存储图片失败-");
      }
      JSONObject res = new JSONObject();
      res.put("url", "http://img.chuyx.top/" + filename);
      res.put("success", 1);
      res.put("message", "upload success!");
      return res.toString();
   }

   @RequestMapping(
      value = {"/uploader/headpic"},
      produces = {"application/json;charset=utf-8"}
   )
   @ResponseBody
   public String uploaderHeadpic(HttpServletRequest request) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
      List<MultipartFile> files = multipartRequest.getFiles("files");
      MultipartFile file = (MultipartFile)files.get(0);
      String filename = "chuyx-" + UUID.randomUUID().toString().replaceAll("-", "");
      String url = "http://img.chuyx.top/" + UploadUtil.uploadQiniu(file);
      HttpSession session = request.getSession();
      return url;
   }
}
