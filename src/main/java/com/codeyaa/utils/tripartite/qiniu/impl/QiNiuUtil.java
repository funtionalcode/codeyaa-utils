package com.codeyaa.utils.tripartite.qiniu.impl;

import com.codeyaa.utils.common.client.CommonClient;
import com.codeyaa.utils.tripartite.qiniu.QiNiu;

import java.io.File;

public class QiNiuUtil implements QiNiu {
    @Override
    public File downloadQiNiuImage(String url, String location, String filename) {
        return CommonClient.downloadImage(url, location, filename);
    }
}
