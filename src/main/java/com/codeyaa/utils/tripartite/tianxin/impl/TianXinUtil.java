package com.codeyaa.utils.tripartite.tianxin.impl;

import com.codeyaa.model.tianxin.vo.AllegoricalResult;
import com.codeyaa.utils.common.client.CommonClient;
import com.codeyaa.utils.tripartite.tianxin.TianXin;

public class TianXinUtil implements TianXin {
    private static final String ALLEGORICAL_URL = "http://api.tianapi.com/txapi/xiehou/index?key=";

    /**
     * show 获取一个歇后语.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param key
     * @Param key
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/24 21:38
     */
    @Override
    public AllegoricalResult getAllegorical(String key) {
        String toText = CommonClient.REQUESTS.get(ALLEGORICAL_URL + key).send().readToText();
        return CommonClient.GSON.fromJson(toText,AllegoricalResult.class);
    }
}
