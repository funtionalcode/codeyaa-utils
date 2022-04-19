package com.codeyaa.model.ip.gaode.vo.json;

import lombok.Data;

import java.io.Serializable;

@Data
public class IpAddressNumModel implements Serializable {

    /**
     * status : 1
     * info : OK
     * infocode : 10000
     * country : 中国
     * province : 广西壮族自治区
     * city : 钦州市
     * district : 钦北区
     * isp : 中国电信
     * location : 108.44911,22.132761
     * ip : 180.141.226.12111
     */

    private String status;
    private String info;
    private String infocode;
    private String country;
    private String province;
    private String city;
    private String district;
    private String isp;
    private String location;
    private String ip;
}
