package com.lyg.lmall.manage.util;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {
        String url = "http://39.98.47.42";
        // 上传图片到服务器
        // 从配置文件中获取tracker的路径
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();
        try {
            ClientGlobal.init(tracker);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();
        // 通过Client获取trackerserver的连接
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


        StorageClient storageClient = new StorageClient(trackerServer, null);
        try {
            byte[] bytes = multipartFile.getBytes();// 获得上传的二进制对象
            // 获得文件后缀名
            String originalFilename = multipartFile.getOriginalFilename();// a.jpg
            System.out.println(originalFilename);
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i+1);

            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);

            for (String uploadInfo : uploadInfos) {
                url += "/"+uploadInfo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }


        return url;
    }
}
