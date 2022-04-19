package com.codeyaa.model.vx.gzh.dto;


import lombok.Data;

/**
 * @author Funtionalcode
 * @className VxMsgModel
 * @description 接收微信公众号发送消息的请求报文
 * @date 2021/5/27 12:10
 */
@Data
public class VxMsgModel {
    /**
     * 取得发送者
     */
    private String fromUsername;
    /**
     * 取得接收者
     */
    private String toUsername;
    /**
     * 系统操作类型
     */
    private String userMsgType;
    /**
     * 搜索题目
     */
    private String keyword;
    /**
     * 微信系统事件类型
     */
    private String event;
    /**
     * 公众号存储的素材id
     */
    private String mediaId;
    /**
     * 公众号存储素材访问地址
     */
    private String mediaUrl;
    /**
     * 白嫖免费接口获取答案内容
     */
    private String content;


}
