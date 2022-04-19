package com.codeyaa.model.ip.geo.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GeoIpAddress implements Serializable {
    /**
     * city : {"geoname_id":1796236,"names":{"pt-BR":"Xangai","ru":"Шанхай","zh-CN":"上海","de":"Shanghai","en":"Shanghai","es":"Shanghai","fr":"Shanghai","ja":"上海"}}
     * continent : {"code":"AS","geoname_id":6255147,"names":{"ja":"アジア","pt-BR":"Ásia","ru":"Азия","zh-CN":"亚洲","de":"Asien","en":"Asia","es":"Asia","fr":"Asie"}}
     * country : {"iso_code":"CN","geoname_id":1814991,"names":{"zh-CN":"中国","de":"China","en":"China","es":"China","fr":"Chine","ja":"中国","pt-BR":"China","ru":"Китай"}}
     * location : {"accuracy_radius":200,"latitude":31.0442,"longitude":121.4054,"time_zone":"Asia/Shanghai"}
     * registered_country : {"iso_code":"CN","geoname_id":1814991,"names":{"pt-BR":"China","ru":"Китай","zh-CN":"中国","de":"China","en":"China","es":"China","fr":"Chine","ja":"中国"}}
     * subdivisions : [{"iso_code":"SH","geoname_id":1796231,"names":{"en":"Shanghai","fr":"Municipalité de Shanghai","pt-BR":"Xangai","zh-CN":"上海"}}]
     * traits : {"autonomous_system_number":24400,"autonomous_system_organization":"Shanghai Mobile Communications Co.,Ltd.","ip_address":"223.104.5.170","network":"223.104.5.0/24"}
     */

    private CityBean city;
    private ContinentBean continent;
    private CountryBean country;
    private LocationBean location;
    private RegisteredCountryBean registered_country;
    private TraitsBean traits;
    private List<SubdivisionsBean> subdivisions;

    @Data
    public static class CityBean implements Serializable {
        /**
         * geoname_id : 1796236
         * names : {"pt-BR":"Xangai","ru":"Шанхай","zh-CN":"上海","de":"Shanghai","en":"Shanghai","es":"Shanghai","fr":"Shanghai","ja":"上海"}
         */

        private Integer geoname_id;
        private NamesBean names;

        @Data
        public static class NamesBean implements Serializable {
            /**
             * pt-BR : Xangai
             * ru : Шанхай
             * zh-CN : 上海
             * de : Shanghai
             * en : Shanghai
             * es : Shanghai
             * fr : Shanghai
             * ja : 上海
             */

            @SerializedName("pt-BR")
            private String ptBR;
            private String ru;
            @SerializedName("zh-CN")
            private String zhCN;
            private String de;
            private String en;
            private String es;
            private String fr;
            private String ja;
        }
    }

    @Data
    public static class ContinentBean implements Serializable {
        /**
         * code : AS
         * geoname_id : 6255147
         * names : {"ja":"アジア","pt-BR":"Ásia","ru":"Азия","zh-CN":"亚洲","de":"Asien","en":"Asia","es":"Asia","fr":"Asie"}
         */

        private String code;
        private Integer geoname_id;
        private NamesBeanX names;

        @Data
        public static class NamesBeanX implements Serializable {
            /**
             * ja : アジア
             * pt-BR : Ásia
             * ru : Азия
             * zh-CN : 亚洲
             * de : Asien
             * en : Asia
             * es : Asia
             * fr : Asie
             */

            private String ja;
            @SerializedName("pt-BR")
            private String ptBR;
            private String ru;
            @SerializedName("zh-CN")
            private String zhCN;
            private String de;
            private String en;
            private String es;
            private String fr;
        }
    }

    @Data
    public static class CountryBean implements Serializable {
        /**
         * iso_code : CN
         * geoname_id : 1814991
         * names : {"zh-CN":"中国","de":"China","en":"China","es":"China","fr":"Chine","ja":"中国","pt-BR":"China","ru":"Китай"}
         */

        private String iso_code;
        private Integer geoname_id;
        private NamesBeanXX names;

        @Data
        public static class NamesBeanXX implements Serializable {
            /**
             * zh-CN : 中国
             * de : China
             * en : China
             * es : China
             * fr : Chine
             * ja : 中国
             * pt-BR : China
             * ru : Китай
             */

            @SerializedName("zh-CN")
            private String zhCN;
            private String de;
            private String en;
            private String es;
            private String fr;
            private String ja;
            @SerializedName("pt-BR")
            private String ptBR;
            private String ru;
        }
    }

    @Data
    public static class LocationBean implements Serializable {
        /**
         * accuracy_radius : 200
         * latitude : 31.0442
         * longitude : 121.4054
         * time_zone : Asia/Shanghai
         */

        private Integer accuracy_radius;
        private Double latitude;
        private Double longitude;
        private String time_zone;
    }

    @Data
    public static class RegisteredCountryBean implements Serializable {
        /**
         * iso_code : CN
         * geoname_id : 1814991
         * names : {"pt-BR":"China","ru":"Китай","zh-CN":"中国","de":"China","en":"China","es":"China","fr":"Chine","ja":"中国"}
         */

        private String iso_code;
        private Integer geoname_id;
        private NamesBeanXXX names;

        @Data
        public static class NamesBeanXXX implements Serializable {
            /**
             * pt-BR : China
             * ru : Китай
             * zh-CN : 中国
             * de : China
             * en : China
             * es : China
             * fr : Chine
             * ja : 中国
             */

            @SerializedName("pt-BR")
            private String ptBR;
            private String ru;
            @SerializedName("zh-CN")
            private String zhCN;
            private String de;
            private String en;
            private String es;
            private String fr;
            private String ja;
        }
    }

    @Data
    public static class TraitsBean implements Serializable {
        /**
         * autonomous_system_number : 24400
         * autonomous_system_organization : Shanghai Mobile Communications Co.,Ltd.
         * ip_address : 223.104.5.170
         * network : 223.104.5.0/24
         */

        private Integer autonomous_system_number;
        private String autonomous_system_organization;
        private String ip_address;
        private String network;
    }

    @Data
    public static class SubdivisionsBean implements Serializable {
        /**
         * iso_code : SH
         * geoname_id : 1796231
         * names : {"en":"Shanghai","fr":"Municipalité de Shanghai","pt-BR":"Xangai","zh-CN":"上海"}
         */

        private String iso_code;
        private Integer geoname_id;
        private NamesBeanXXXX names;

        @Data
        public static class NamesBeanXXXX implements Serializable {
            /**
             * en : Shanghai
             * fr : Municipalité de Shanghai
             * pt-BR : Xangai
             * zh-CN : 上海
             */

            private String en;
            private String fr;
            @SerializedName("pt-BR")
            private String ptBR;
            @SerializedName("zh-CN")
            private String zhCN;
        }
    }
}
