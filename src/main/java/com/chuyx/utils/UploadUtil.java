package com.chuyx.utils;

import com.chuyx.constant.NormalConstant;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 七牛云oss工具类
 *
 * @author yasir.chu
 */
public class UploadUtil {

   /**
    * 上传
    *
    * @param file 上传文件
    * @return 下载路径
    */
   public static String uploadQiniu(MultipartFile file) {
      Configuration cfg = new Configuration(Zone.zone2());
      UploadManager uploadManager = new UploadManager(cfg);
      String bucket = "chuyx";
      String key = UUID.randomUUID().toString();
      Auth auth = Auth.create(NormalConstant.QI_NIU_ACCESS_KEY, NormalConstant.QI_NIE_SECRET_KEY);
      String upToken = auth.uploadToken(bucket);

      try {
         Response response = uploadManager.put(file.getBytes(), key, upToken);
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
