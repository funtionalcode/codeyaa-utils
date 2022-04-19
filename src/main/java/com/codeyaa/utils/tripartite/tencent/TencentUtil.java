package com.codeyaa.utils.tripartite.tencent;

import java.io.File;
import java.io.InputStream;

public interface TencentUtil {

    InputStream getBill(String url);

    File getBillFile(String url);

    /**
     * @Description: 腾讯云对象存储下载
     * @Param: bucketName Bucket的命名格式为 BucketName-APPID
     * @Param: key 例：user/13024397891/068f662815783cc1dafbd4fb5efe0c1c.pdf
     * @Param: fileName
     * @Return: void
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/22 21:17
     */
    void downloadFile(String bucketName, String key, String rootPath);

    /**
     * @Description: 上传文件
     * @Param: key
     * @Param: fileInputStream 文件输入流
     * @Return: void
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/23 08:40
     */
    void uploadFile(String key, InputStream fileInputStream);
}
