package com.chuyx.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.chuyx.utils.UploadUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author yasir.chu
 */
@Controller
@Slf4j
public class FileController {

   @RequestMapping(value = {"/file/upload"}, produces = {"application/json;charset=utf-8"})
   @ResponseBody
   public String fileUpload(@RequestParam(value = "editormd-image-file") MultipartFile file) throws JSONException {
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

   @RequestMapping(value = {"/uploader/headPic"}, produces = {"application/json;charset=utf-8"})
   @ResponseBody
   public String uploaderHeadPic(HttpServletRequest request) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
      List<MultipartFile> files = multipartRequest.getFiles("files");
      if (CollectionUtils.isEmpty(files)){
         log.warn("没有上传文件");
         return null;
      }
      MultipartFile file = files.get(0);
      return "http://img.chuyx.top/" + UploadUtil.uploadQiniu(file);
   }
}
