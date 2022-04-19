package com.codeyaa;

import com.codeyaa.model.ip.gaode.vo.xml.IpaddressXml;
import com.codeyaa.model.vx.gzh.dto.xml.RequestBodyXml;
import com.codeyaa.utils.common.FileUtil;
import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Funtionalcode
 * @classname XMLTest
 * @description TODO
 * @date 2021/8/5 11:09
 */
public class XMLTest {
    public static void main(String[] args) {
        decoder();
    }

    @SneakyThrows
    private static void read() {
        String xml = FileUtil.readFile("C:\\Users\\codeyaa\\Desktop\\gaode.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(IpaddressXml.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        IpaddressXml ipaddressXml = (IpaddressXml) unmarshaller.unmarshal(new StringReader(xml));
//        System.out.println(ipaddressXml);
        toXmlStr(ipaddressXml);
    }

    @SneakyThrows
    private static void toXmlStr(IpaddressXml ipaddressXml) {
        JAXBContext context = JAXBContext.newInstance(RequestBodyXml.class);
        RequestBodyXml requestBodyXml = new RequestBodyXml();
        requestBodyXml.setToUserName("1");
        requestBodyXml.setFromUserName("2");
        requestBodyXml.setCreateTime("3");
        requestBodyXml.setMsgType("4");
        requestBodyXml.setEvent("5");
        requestBodyXml.setLatitude("6");
        requestBodyXml.setLongitude("7");
        requestBodyXml.setPrecision("8");
        requestBodyXml.setContent("11");
        requestBodyXml.setMediaId("12");
        requestBodyXml.setPicUrl("13");

        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(requestBodyXml, System.out);
    }

    private static void decoder() {
        String coder = FileUtil.readFile("C:\\Users\\codeyaa\\Desktop\\新建文本文档.txt");
        String res = new String(Base64.getMimeDecoder().decode(coder.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        System.out.println(res);
    }

}
