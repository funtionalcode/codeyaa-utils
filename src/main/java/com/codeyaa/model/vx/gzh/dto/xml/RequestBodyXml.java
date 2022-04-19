package com.codeyaa.model.vx.gzh.dto.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class RequestBodyXml {
    @XmlElement(name = "ToUserName")
    private String toUserName;
    @XmlElement(name = "FromUserName")
    private String fromUserName;
    @XmlElement(name = "CreateTime")
    private String createTime;
    @XmlElement(name = "MsgType")
    private String msgType;
    @XmlElement(name = "Event")
    private String event;
    @XmlElement(name = "Latitude")
    private String latitude;
    @XmlElement(name = "Longitude")
    private String longitude;
    @XmlElement(name = "Precision")
    private String precision;
    @XmlElement(name = "Content")
    private String content;
    @XmlElement(name = "MediaId")
    private String mediaId;
    @XmlElement(name = "PicUrl")
    private String picUrl;
}
