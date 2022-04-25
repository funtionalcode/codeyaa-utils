package com.codeyaa.utils.tripartite.tencent.impl;

import com.codeyaa.utils.common.client.CommonClient;
import com.codeyaa.utils.tripartite.tencent.TencentUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dongliu.requests.Requests;
import net.dongliu.requests.Session;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @Classname TencentUtilImpl
 * @Description TODO
 * @Date 2021/5/24 18:37
 * @Created by DELL
 */
@Data
@NoArgsConstructor
public class TencentUtilImpl implements TencentUtil {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;
    private COSClient cosClient;
    private final static Session REQUESTS = Requests.session();

    public TencentUtilImpl(String secretId, String secretKey, String region) {
        this.secretId = secretId;
        this.secretKey = secretKey;
        this.region = region;
        loadCOSClient();
    }

    /**
     * @Description: 加载腾讯 SDK 客户端
     * @Param:
     * @Return: void
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/23 17:04
     */
    private void loadCOSClient() {
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        clientConfig.setHttpProtocol(HttpProtocol.https);
        cosClient = new COSClient(cred, clientConfig);
    }

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
    @Override
    public void downloadFile(String bucketName, String key, String rootPath) {
        String[] names = key.split("/");
        File downFile = new File(rootPath + "/" + names[2]);
        if (!downFile.exists()) {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            cosClient.getObject(getObjectRequest, downFile);
        }
    }

    /**
     * @Description: 上传文件
     * @Param: key
     * @Param: fileInputStream 文件输入流
     * @Return: void
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/6/23 08:40
     */
    @Override
    public void uploadFile(String key, InputStream fileInputStream) {
        PutObjectRequest putRequest = new PutObjectRequest(bucketName, key, fileInputStream, null);
        cosClient.putObject(putRequest);
    }

    @Override
    public InputStream getBill(String url) {
        byte[] bytes = REQUESTS.get(url).send().readToBytes();
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public File getBillFile(String url) {
        return CommonClient.downloadImage(url, "d:/", "shop.pdf");
    }
}
