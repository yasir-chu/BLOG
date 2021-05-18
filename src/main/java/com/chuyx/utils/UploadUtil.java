package com.chuyx.utils;

import com.chuyx.constant.NormalConstant;
import com.google.common.base.Throwables;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 七牛云oss工具类
 *
 * @author yasir.chu
 */
@Slf4j
public class UploadUtil {

   /**
    * 上传
    *
    * @param file 上传文件
    * @return 文件名
    */public static String uploadQiniu(MultipartFile file) {
      try {
         log.info("七牛云上传图片开始-文件名为-{}", file.getName());
         Configuration cfg = new Configuration(Zone.zone2());
         UploadManager uploadManager = new UploadManager(cfg);
         String bucket = "chuyx";
         String key = UUID.randomUUID().toString();
         Auth auth = Auth.create(NormalConstant.QI_NIU_ACCESS_KEY, NormalConstant.QI_NIE_SECRET_KEY);
         String upToken = auth.uploadToken(bucket);

         Response response = uploadManager.put(file.getBytes(), key, upToken);
         DefaultPutRet putRet = (new Gson()).fromJson(response.bodyString(), DefaultPutRet.class);
         return putRet.key;
      } catch (QiniuException e) {
         log.error("七牛云上传文件出错-{}", Throwables.getStackTraceAsString(e));
      } catch (IOException e) {
         log.error("文件转字节出错-{}", Throwables.getStackTraceAsString(e));
      }
      return null;
   }

}
