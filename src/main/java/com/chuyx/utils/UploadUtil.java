package com.chuyx.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtil {
   public static String uploadQiniu(MultipartFile file) {
      Configuration cfg = new Configuration(Zone.zone2());
      UploadManager uploadManager = new UploadManager(cfg);
      String accessKey = "5ngLMovI40oojUYkdSvnORe0JtpY4yH3zjpB7yjr";
      String secretKey = "FvDyWzPUobu5ctFofYFfyah91vAXcOm7CBv1nbhz";
      String bucket = "chuyx";
      String key = null;
      Auth auth = Auth.create(accessKey, secretKey);
      String upToken = auth.uploadToken(bucket);

      try {
         Response response = uploadManager.put(file.getBytes(), (String)key, upToken);
         DefaultPutRet putRet = (DefaultPutRet)(new Gson()).fromJson(response.bodyString(), DefaultPutRet.class);
         return putRet.key;
      } catch (QiniuException var13) {
         Response r = var13.response;
         System.err.println(r.toString());

         try {
            System.err.println(r.bodyString());
         } catch (QiniuException var12) {
         }
      } catch (Exception var14) {
         var14.printStackTrace();
      }

      return null;
   }
}
