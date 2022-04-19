package com.codeyaa;

import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.utils.tripartite.qiniu.impl.QiNiuUtil;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QnTest {
    private static BucketManager bucketManager;
    private static String bucket = "";

    static {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region2());
        String[] split = FileUtil.readFile("d:/sk/qiniu.txt").split("\r\n");
        //...其他参数参考类注释
        String accessKey = split[0];
        String secretKey = split[1];
        bucket = split[2];
        Auth auth = Auth.create(accessKey, secretKey);
        bucketManager = new BucketManager(auth, cfg);
    }

    public static void main(String[] args) {
//        downloadImage();
//        downloadVioce();
        getImageList();
    }

    private static void getImageRestApi() {

    }

    @SneakyThrows
    private static void getImageList() {
        //文件名前缀
        String prefix = "";
        //每次迭代的长度限制，最大1000，推荐值 1000
        int limit = 1000;
        //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
        String delimiter = "";
        //列举空间文件列表 rs.qiniu.com
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
        ArrayList<String> res = new ArrayList<>();
        while (fileListIterator.hasNext()) {
            //处理获取的file list结果
            FileInfo[] items = fileListIterator.next();
            for (FileInfo item : items) {
                res.add(item.key);
                /*  System.out.println(item.hash);
                System.out.println(item.fsize);
                System.out.println(item.mimeType);
                System.out.println(item.putTime);
                System.out.println(item.endUser);*/
            }
        }
        List<String> list = res.stream().filter(row -> !row.contains(".")).collect(Collectors.toList());
//        list.forEach(System.out::println);
        downloadImage(list);
    }

    @SneakyThrows
    private static void downloadImage(List<String> names) {
        for (int i = 0; i < names.size(); i++) {
            System.out.println(i);
            String name = names.get(i);
            QiNiuUtil qiNiuUtil = new QiNiuUtil();
            String fix = ".png";
            String url = "http://qiniuoss.codeyaa.com/" + name;
            String location = "d:\\img";
            File file = new File(location + "\\" + name + fix);
            if (!file.exists()) {
                qiNiuUtil.downloadQiNiuImage(url, location, name + fix);
            }
        }
    }

    @SneakyThrows
    private static void downloadVioce() {
        String name = "666";
        QiNiuUtil qiNiuUtil = new QiNiuUtil();
        String fix = ".mp4";
        String url = "https://sf1-ttcdn-tos.pstatp.com/obj/tos-cn-v-0004/f3e635df5c97486aa3e461fc6f7c3f86";
        String location = "d:\\img666";
        File file = new File(location + "\\" + name + fix);
        if (!file.exists()) {
            qiNiuUtil.downloadQiNiuImage(url, location, name + fix);
        }
    }
}
