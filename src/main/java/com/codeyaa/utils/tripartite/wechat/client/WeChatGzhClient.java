package com.codeyaa.utils.tripartite.wechat.client;

import com.codeyaa.model.vx.gzh.dto.SendMsgRequest;
import com.codeyaa.model.vx.gzh.vo.ErrorResult;
import com.codeyaa.model.vx.gzh.vo.UploadImgResultl;
import com.codeyaa.utils.common.client.CommonClient;
import com.codeyaa.utils.tripartite.http.requests.RequestsUtil;
import net.dongliu.requests.body.Part;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WeChatGzhClient extends CommonClient {

    public static UploadImgResultl uploadImage(String url, String filePath) {
        try {
            Part<?> media = RequestsUtil.getPart("media", new FileInputStream(filePath));
            return REQUESTS.post(url).multiPartBody(media).send().readToJson(UploadImgResultl.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new UploadImgResultl();
        }
    }

    public static ErrorResult sendMsg(String url, SendMsgRequest setMsg) {
        String token = setMsg.getToken();
        return REQUESTS.post(url + token).send().readToJson(ErrorResult.class);
    }
    public InputStream downloadImageToInputStream(String url){
        byte[] bytes = REQUESTS.get(url).send().readToBytes();
        return new ByteArrayInputStream(bytes);
    }
}
