package com.codeyaa.utils.tripartite.ip.gaode;

import com.codeyaa.model.ip.gaode.vo.json.IpAddressModel;
import com.codeyaa.model.ip.gaode.vo.json.IpAddressNumModel;
import com.codeyaa.model.ip.gaode.IpBase;
import com.codeyaa.model.ip.gaode.vo.xml.IpaddressXml;
import com.codeyaa.utils.common.FileUtil;
import com.google.gson.Gson;
import lombok.Data;
import net.dongliu.requests.Requests;

import java.util.Optional;

@Data
public class GaodeIpUtil {
    private final String ipAddressNumUrl = "https://restapi.amap.com/v5/ip";
    private final String ipAddressUrl = "https://restapi.amap.com/v3/geocode/regeo";
    private String key;
    private Integer type = 4;
    private static final Gson GSON = new Gson();

    public GaodeIpUtil(String key) {
        this.key = key;
    }

    public IpAddressNumModel getIpAddressNum(String ip) {
        String url = String.format("%s?key=%s&type=%s&ip=%s", ipAddressNumUrl, key, type, ip);
        IpAddressNumModel addressNumModel = Optional.ofNullable(Requests.session().get(url).send().readToText())
                .map(res -> res.replace("[]", "\"\""))
                .map(res -> res.replace("[\"\"]", "[]"))
                .map(body -> GSON.fromJson(body, IpAddressNumModel.class))
                .orElse(new IpAddressNumModel());
        return addressNumModel;
    }

    public IpBase getIpAddress(String location, String output) {
        if (!"json".equalsIgnoreCase(output) && !"xml".equalsIgnoreCase(output)) {
            output = "json";
        }
        String url = String.format("%s?output=%s&location=%s&key=%s&redius=%s&extensions=&s",
                ipAddressUrl, output, location, key, 1000, "all");
        String body = Requests.session().get(url).send()
                .readToText();
        if ("json".equalsIgnoreCase(output)) {
            return toJson(body);
        } else if ("xml".equalsIgnoreCase(output)) {
            return toXmlBean(body);
        }
        return new IpAddressModel();
    }

    private IpAddressModel toJson(String body) {
        IpAddressModel ipAddress = Optional.ofNullable(body)
                .map(res -> res.replace("[]", "\"\""))
                .map(res -> res.replace("[\"\"]", "[]"))
                .map(city -> GSON.fromJson(city, IpAddressModel.class))
                .orElse(new IpAddressModel());
        return ipAddress;
    }

    private IpaddressXml toXmlBean(String body) {
        IpaddressXml ipAddress = Optional.ofNullable(FileUtil.convertToXMLBean(body, IpaddressXml.class))
                .orElse(new IpaddressXml());
        return ipAddress;
    }
}
