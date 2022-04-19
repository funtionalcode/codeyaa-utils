package com.codeyaa.utils.common.wxgzh;


import com.codeyaa.model.vx.gzh.dto.VxCheckRequest;
import com.codeyaa.model.vx.gzh.dto.VxMsgModel;
import com.codeyaa.utils.common.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author Funtionalcode
 * @className WXRequestParamUtil
 * @description 微信公众号转换实体工具类
 * @date 2021/5/27 12:11
 */
public class WXRequestParamUtil {

    /**
     * @param in             request 输入流
     * @param vxCheckRequest 接收公众号发送的参数封装实体类
     * @param pc             微信官方加解密工具类
     * @param encode         公众号接口是否为安全模式
     * @return 从输入流读取post xml参数
     */
    public static String readStreamParameter(InputStream in, VxCheckRequest vxCheckRequest, WXBizMsgCrypt pc, boolean encode) {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        String res = "";
        try {
            reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (StringUtils.isBlank(buffer.toString())) {
                res = buffer.toString();
                return res;
            }
            if (encode) {
                res = pc.decryptMsg(vxCheckRequest.getMsg_signature(), vxCheckRequest.getTimestamp(), vxCheckRequest.getNonce(), buffer.toString());
            } else {
                res = buffer.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    /**
     * @param postStr xml 格式 string
     * @return 从微信公众号发送的 xml 报文中解析并封装实体类返回
     */
    public static VxMsgModel parseVxMsgModel(String postStr) {
        Document document = null;
        try {
            if (!"".equals(postStr.trim())) {
                document = DocumentHelper.parseText(postStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == document) {
            return new VxMsgModel();
        }
        Element root = document.getRootElement();
        //取得发送者
        String fromUsername = root.elementText("FromUserName");
        //取得接收者
        String toUsername = root.elementText("ToUserName");
        String userMsgType = root.elementText("MsgType");
        String keyword = root.elementTextTrim("Content");
        String event = root.elementTextTrim("Event");
        String mediaId = root.elementTextTrim("MediaId");
        String picUrl = root.elementTextTrim("PicUrl");
        String content = root.elementTextTrim("Content");

        VxMsgModel vxMsgModel = new VxMsgModel();
        vxMsgModel.setFromUsername(fromUsername);
        vxMsgModel.setToUsername(toUsername);
        vxMsgModel.setUserMsgType(userMsgType);
        vxMsgModel.setKeyword(keyword);
        if (StringUtils.isNotBlank(event)) {
            vxMsgModel.setEvent(event);
        }
        if (StringUtils.isNotBlank(mediaId)) {
            vxMsgModel.setMediaId(mediaId);
        }
        if (StringUtils.isNotBlank(picUrl)) {
            vxMsgModel.setMediaUrl(picUrl);
        }
        if (StringUtils.isNotBlank(content)) {
            vxMsgModel.setContent(content);
        }
        return vxMsgModel;
    }
}
