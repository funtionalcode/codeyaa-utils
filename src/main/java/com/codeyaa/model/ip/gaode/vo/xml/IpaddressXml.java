package com.codeyaa.model.ip.gaode.vo.xml;

import com.codeyaa.model.ip.gaode.IpBase;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class IpaddressXml extends IpBase {
    private Integer status;
    private String info;
    @XmlElement(name = "infocode")
    private String infoCode;
    @XmlElement(name = "regeocode")
    private RegeoCodeBean regeoCode;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class RegeoCodeBean {
        @XmlElement(name = "formatted_address")
        private String formattedAddress;
        private AddressComponent addressComponent;
        private Pois pois;
        private Roads roads;
        private Roadinters roadinters;
        private Aois aois;

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Aois {
            private List<Aoi> aoi;

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Aoi {
                private String id;
                private String name;
                private String adcode;
                private String location;
                private String area;
                private String distance;
                private String type;
            }
        }

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Roadinters {
            private List<Roadinter> roadinter;

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Roadinter {
                private String direction;
                private String distance;
                private String location;
                @XmlElement(name = "first_id")
                private String firstId;
                @XmlElement(name = "first_name")
                private String firstname;
                @XmlElement(name = "second_id")
                private String secondId;
                @XmlElement(name = "second_name")
                private String secondName;
            }
        }

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Roads {
            private List<Road> road;

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Road {
                private String id;
                private String name;
                private String direction;
                private String distance;
                private String location;
            }
        }

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Pois {
            private List<Poi> poi;

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Poi {
                private String id;
                private String name;
                private String type;
                private String tel;
                private String direction;
                private String distance;
                private String location;
                private String address;
                @XmlElement(name = "poiweight")
                private String poiWeight;
                @XmlElement(name = "businessarea")
                private String businessArea;

            }
        }

        @Data
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class AddressComponent {
            private String country; // 国家
            private String province; // 城市
            private String city;
            @XmlElement(name = "citycode")
            private String cityCode; // 城市代码
            private String district; // 街区
            @XmlElement(name = "adcode")
            private String adCode; // 街区代码

            private String township; // 街道
            @XmlElement(name = "towncode")
            private String townCode; // 街道代码
            private Neighborhood neighborhood;
            private Building building;
            private StreetNumber streetNumber;
            private BusinessAreas businessAreas;

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Neighborhood {
                private String name;
                private String type;
            }

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class Building {
                private String name;
                private String type;
            }

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class StreetNumber {
                private String street; // 路
                private String number; // 路号
                private String location; // 坐标
                private String direction; // 位置 (东南西北)
                private String distance; // 位置角度
            }

            @Data
            @XmlAccessorType(XmlAccessType.FIELD)
            public static class BusinessAreas {
                private List<BusinessArea> businessArea;

                @Data
                @XmlAccessorType(XmlAccessType.FIELD)
                public static class BusinessArea {
                    private String location;
                    private String name;
                    private String id;
                }
            }

        }
    }
}
