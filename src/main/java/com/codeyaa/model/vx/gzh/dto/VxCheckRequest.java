package com.codeyaa.model.vx.gzh.dto;

import lombok.Data;

/**
 * @author Funtionalcode
 * @className VxCheckRequest
 * @description 接收微信公众号校验服务器的请求报文
 * @date 2021/5/27 12:09
 */
@Data
public class VxCheckRequest {
    /**
     * 公众号服务端发送的验证字符串
     */
    private String signature;
    /**
     * 当前时间戳
     */
    private String timestamp;
    /**
     * 公众号服务端发送的验证参数
     */
    private String nonce;
    /**
     * 公众号服务端发送的验证待返回公众号服务端的参数
     */
    private String echostr;
    /**
     * 用户再公众号的唯一Id
     */
    private String openid;
    /**
     * 公众号接口加密的安全模式，加密字符串
     */
    private String encrypt;
    /**
     * 公众号接口加密的安全模式，验证字符串
     */
    private String msg_signature;

    @Override
    public String toString() {
        return "{" +
                "signature='" + signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", echostr='" + echostr + '\'' +
                ", openid='" + openid + '\'' +
                ", encrypt='" + encrypt + '\'' +
                ", msg_signature='" + msg_signature + '\'' +
                '}';
    }
}
