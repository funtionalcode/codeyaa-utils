package com.codeyaa.utils.tripartite.ip.geolite;

import com.codeyaa.model.ip.geo.vo.GeoIpAddress;
import com.codeyaa.utils.common.MD5Util;
import com.google.gson.Gson;
import net.dongliu.requests.Header;
import net.dongliu.requests.Requests;

import java.util.Optional;

public class GeoIpUtil {
    private String username;
    private String password;
    private final String preUrl = "https://geolite.info/geoip/v2.1/city/";
    private final String fix = "?pretty";
    private static final Gson GSON = new Gson();

    public GeoIpUtil(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getLocation(String ip){
        String locationRes = Optional.ofNullable(getIpAddress(ip))
                .map(GeoIpAddress::getLocation)
                .map(location -> String.format("%s,%s", location.getLongitude(), location.getLatitude()))
                .orElse("");
        return locationRes;
    }
    public GeoIpAddress getIpAddress(String ip) {
        String url = String.format("%s%s%s", preUrl, ip, fix);
        String auth = MD5Util.makeAuth(username,password);
        GeoIpAddress ipAddress = Optional.ofNullable(Requests.session().get(url).headers(new Header("Authorization",auth)).send().readToText())
                .map(body -> GSON.fromJson(body, GeoIpAddress.class))
                .orElse(new GeoIpAddress());
        return ipAddress;
    }
}
