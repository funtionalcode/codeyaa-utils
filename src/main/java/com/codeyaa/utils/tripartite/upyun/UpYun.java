package com.codeyaa.utils.tripartite.upyun;

import com.codeyaa.model.upyun.vo.ImageListResult;
import okhttp3.Response;

import java.io.InputStream;

public interface UpYun {

    Response uploadImage(String upyunPath, InputStream inputStream, String fileName, String secret);

    Response uploadImageNoSecret(String upyunPath, InputStream inputStream, String fileName);

    Response deleteImage(String upyunPath);

    /**
     * show 获取指定路径下最大 10000 张图片信息
     *
     * @return
     * @Param upyunPath  又拍云文件夹路径
     * @Return
     * @Author xin11.xin
     * @Date 2021/9/3 2:16
     */
    ImageListResult getImageListTen(String upyunPath);
}
