package com.codeyaa;

import com.codeyaa.model.ip.gaode.vo.json.IpAddressNumModel;
import com.codeyaa.model.ip.gaode.vo.xml.IpaddressXml;
import com.codeyaa.utils.tripartite.ip.gaode.GaodeIpUtil;
import com.codeyaa.utils.tripartite.ip.geolite.GeoIpUtil;

import java.util.Optional;

public class AuthTest {
    public static void main(String[] args) {
        makeAuth();
    }

    private static void makeAuth() {
        String key = "";
        String username = "";
        String password = "";
        GaodeIpUtil gaodeIpUtil = new GaodeIpUtil(key);
        String ip = "101.200.33.252";
        GeoIpUtil geoIpUtil = new GeoIpUtil(username, password);
        String location = Optional.ofNullable(gaodeIpUtil.getIpAddressNum(ip))
                .map(IpAddressNumModel::getLocation)
                .orElse(geoIpUtil.getLocation(ip));
        String address = Optional.ofNullable(gaodeIpUtil.getIpAddress(location, "xml"))
                .map(res -> (IpaddressXml) res)
                .map(IpaddressXml::getRegeoCode)
                .map(IpaddressXml.RegeoCodeBean::getFormattedAddress)
                .orElse("");
        System.out.println(address);
    }
}
