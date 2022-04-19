package com.codeyaa;

import com.codeyaa.model.upyun.vo.ImageListResult;
import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.utils.tripartite.upyun.UpYun;
import com.codeyaa.utils.tripartite.upyun.impl.UpYunUtil;
import lombok.SneakyThrows;
import okhttp3.Response;

import java.io.File;
import java.io.FileInputStream;

public class UpYunTest {
    private static UpYun upYun;

    static {
//        String[] split = FileUtil.readFile("d:/sk/upyun.txt").split("\r\n");
//        upYun = new UpYunUtil(split[0], split[1], split[2]);
    }

    public static void main(String[] args) {
//        getList();
        uploadResApi();
//        deleteResApi();
    }


    @SneakyThrows
    private static void getList() {
        ImageListResult imageListTen = upYun.getImageListTen("//blog");
        System.out.println(imageListTen);
    }

    private static void deleteResApi() {
        Response response = upYun.deleteImage("/1.png");
        System.out.println("response = " + response);
    }

    @SneakyThrows
    private static void uploadResApi() {
        File file = new File("C:\\Users\\Administrator\\Pictures\\66666.png");
        String name = System.currentTimeMillis() + ".png";
        FileInputStream fileInputStream = new FileInputStream(file);
        Response response = upYun.uploadImage("/", fileInputStream, name, "haogege");
        System.out.println("response = " + response);
        System.out.println("https://haoxue.xin11.xin/" + name + "!haogege");
    }
}
