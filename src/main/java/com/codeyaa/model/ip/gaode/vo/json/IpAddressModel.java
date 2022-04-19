package com.codeyaa.model.ip.gaode.vo.json;

import com.codeyaa.model.ip.gaode.IpBase;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class IpAddressModel extends IpBase implements Serializable {

    /**
     * status : 1
     * regeocode : {"roads":[{"id":"0777F49F023004130","location":"108.448,22.1323","direction":"东","name":"解放路","distance":"131.03"},{"id":"0777F49F023004600037","location":"108.451,22.1332","direction":"西","name":"望海大道","distance":"162.318"},{"id":"0777F49F02200326","location":"108.448,22.1334","direction":"东","name":"300县道","distance":"171.835"}],"roadinters":[{"second_name":"解放路","first_id":"0777F49F02200326","second_id":"0777F49F023004130","location":"108.447573,22.133362","distance":"171.835","first_name":"300县道","direction":"东"}],"formatted_address":"广西壮族自治区钦州市钦北区大寺镇钦北区","addressComponent":{"city":"钦州市","province":"广西壮族自治区","adcode":"450703","district":"钦北区","towncode":"450703110000","streetNumber":{"number":"4号","location":"108.448898,22.132871","direction":"西北","distance":"25.0248","street":"解放路"},"country":"中国","township":"大寺镇","businessAreas":[[]],"building":{"name":[],"type":[]},"neighborhood":{"name":[],"type":[]},"citycode":"0777"},"aois":[],"pois":[{"id":"B0309001EJ","direction":"西南","businessarea":[],"address":"大寺镇解放西路73号","poiweight":"0.373842","name":"大寺中心小学","location":"108.447669,22.130226","distance":"318.589","tel":"0777-5718510","type":"科教文化服务;学校;小学"},{"id":"B030900MTG","direction":"西南","businessarea":[],"address":"218省道与300县道交叉口东南200米","poiweight":"0.087371","name":"大寺村委卫生所","location":"108.448226,22.132099","distance":"117.099","tel":[],"type":"医疗保健服务;诊所;诊所"},{"id":"B0FFHUINNN","direction":"西北","businessarea":[],"address":"大寺镇安发大街34号","poiweight":"0.178596","name":"应钟幼儿园","location":"108.448727,22.133278","distance":"69.7229","tel":[],"type":"科教文化服务;学校;幼儿园"},{"id":"B030900DZZ","direction":"Center","businessarea":[],"address":"钦北区","poiweight":"0.600366","name":"钦北区","location":"108.449110,22.132761","distance":"0","tel":[],"type":"地名地址信息;普通地名;区县级地名"},{"id":"B0FFGUO614","direction":"东南","businessarea":[],"address":[],"poiweight":"0.0618232","name":"祥盛小区","location":"108.450131,22.131548","distance":"171.052","tel":[],"type":"商务住宅;住宅区;住宅小区"},{"id":"B0FFFZE2KC","direction":"东北","businessarea":[],"address":"218省道东100米","poiweight":"0.129781","name":"万宁床垫","location":"108.451152,22.133668","distance":"233.261","tel":"13977785380;13977739586","type":"购物服务;家居建材市场;家具城"},{"id":"B0FFHEWWWQ","direction":"西","businessarea":[],"address":"218省道与300县道交叉口南50米","poiweight":"0.182294","name":"大寺供销社鑫华幼儿园","location":"108.447285,22.133121","distance":"192.201","tel":"0777-5710000;0777-5719999","type":"科教文化服务;学校;幼儿园"},{"id":"B0FFF2Y4NC","direction":"东南","businessarea":[],"address":"福旺镇六中路口","poiweight":"0.183522","name":"幸福幼儿园","location":"108.451489,22.130314","distance":"366.198","tel":"0777-5725388;13977771677","type":"科教文化服务;学校;幼儿园"},{"id":"B0FFL1CX25","direction":"东","businessarea":[],"address":"望海大道(幸福幼儿园北侧约200米)","poiweight":"0.195672","name":"童之梦幼儿园","location":"108.451097,22.132501","distance":"206.699","tel":[],"type":"科教文化服务;学校;幼儿园"},{"id":"B0FFFZDUVB","direction":"西北","businessarea":[],"address":"安达西大街33号","poiweight":"0.205382","name":"锦筵大酒店","location":"108.448670,22.133586","distance":"102.325","tel":"0777-5713333;0777-5715555","type":"住宿服务;宾馆酒店;宾馆酒店"},{"id":"B0FFH6QL1S","direction":"北","businessarea":[],"address":"大寺镇镇北3路87号","poiweight":"0.182388","name":"大风车幼儿园","location":"108.448559,22.134755","distance":"228.872","tel":"13877729993","type":"科教文化服务;学校;幼儿园"},{"id":"B03090MAVW","direction":"东北","businessarea":[],"address":"大寺镇望海大道91号","poiweight":"0.24998","name":"钦州市钦北区大寺第二中学","location":"108.450583,22.136043","distance":"395.238","tel":[],"type":"科教文化服务;学校;中学"},{"id":"B0FFLCVABD","direction":"东","businessarea":[],"address":"望海大道(大寺兴华市场南侧约100米)","poiweight":"0.176325","name":"国发医药(大寺店)","location":"108.450962,22.133156","distance":"195.753","tel":[],"type":"医疗保健服务;医药保健销售店;药房"},{"id":"B0FFLCWNRT","direction":"西南","businessarea":[],"address":"大寺镇218省道与311省道交叉口东200米","poiweight":"0.147192","name":"顺发大排档","location":"108.447963,22.131220","distance":"208.149","tel":"0777-5719628","type":"餐饮服务;餐饮相关场所;餐饮相关"},{"id":"B0FFFZE0T4","direction":"北","businessarea":[],"address":"大寺镇望海大道","poiweight":"0.155107","name":"廖康药店","location":"108.449844,22.135283","distance":"290.45","tel":[],"type":"医疗保健服务;医药保健销售店;药房"},{"id":"B0FFF5W2M2","direction":"南","businessarea":[],"address":"三沿路33号","poiweight":"0.219819","name":"中国信合(镇中分社)","location":"108.450398,22.128580","distance":"483.5","tel":"0777-5718351;0777-5718462","type":"金融保险服务;银行;农村商业银行"},{"id":"B0FFLCPSKH","direction":"南","businessarea":[],"address":"解放路(大寺市场东北侧约100米)","poiweight":"0.204403","name":"功德楼","location":"108.450118,22.129632","distance":"363.126","tel":[],"type":"商务住宅;楼宇;楼宇相关"},{"id":"B0FFF5W2LY","direction":"南","businessarea":[],"address":"大寺镇解放西路4号","poiweight":"0.243017","name":"中国农业银行(钦州大寺支行)","location":"108.450557,22.128330","distance":"514.788","tel":"0777-5718351","type":"金融保险服务;银行;中国农业银行"},{"id":"B030900MR9","direction":"东北","businessarea":[],"address":"大寺镇望海大道215号","poiweight":"0.108275","name":"大寺兴华市场","location":"108.451120,22.134793","distance":"306.464","tel":[],"type":"购物服务;综合市场;综合市场"},{"id":"B03090MNH0","direction":"南","businessarea":[],"address":"大寺镇解放路","poiweight":"0.308906","name":"平康药店","location":"108.448617,22.129630","distance":"351.864","tel":[],"type":"医疗保健服务;医药保健销售店;药房"},{"id":"B0FFGM3QVP","direction":"南","businessarea":[],"address":"旧圩江南市场河边木棉花","poiweight":"0.476845","name":"北帝庙","location":"108.450437,22.129451","distance":"392.632","tel":[],"type":"风景名胜;风景名胜;寺庙道观"},{"id":"B0FFLCQ4CB","direction":"东南","businessarea":[],"address":[],"poiweight":"0.278199","name":"北帝庙综合楼","location":"108.450613,22.129514","distance":"392.884","tel":[],"type":"风景名胜;风景名胜;寺庙道观"},{"id":"B0FFFZAJWO","direction":"南","businessarea":[],"address":"中山路西50米","poiweight":"0.200468","name":"钦北利民印刷厂","location":"108.447916,22.129293","distance":"404.782","tel":"0777-5718365;13877758336","type":"公司企业;工厂;工厂"},{"id":"B0FFFZDUWP","direction":"东南","businessarea":[],"address":"218省道东50米","poiweight":"0.18074","name":"育苗幼儿园","location":"108.452750,22.129293","distance":"537.875","tel":[],"type":"科教文化服务;学校;幼儿园"},{"id":"B030900MTY","direction":"北","businessarea":[],"address":"望海大道","poiweight":"0.48","name":"大寺客运站","location":"108.450231,22.138143","distance":"609.512","tel":[],"type":"交通设施服务;长途汽车站;长途汽车站"},{"id":"B0FFLBY5U0","direction":"东","businessarea":[],"address":"钦北区人民法院大寺人民法庭","poiweight":"0.204979","name":"钦北区人民法院壮语巡回法庭","location":"108.453245,22.133719","distance":"439.043","tel":[],"type":"政府机构及社会团体;公检法机构;法院"},{"id":"B0FFFZADIW","direction":"东南","businessarea":[],"address":"解放路(大寺市场东南侧约250米)","poiweight":"0.0674643","name":"钦北区人民检察院大寺检查室","location":"108.451355,22.127772","distance":"601.062","tel":[],"type":"政府机构及社会团体;公检法机构;检察院"},{"id":"B0FFLBXYZD","direction":"东","businessarea":[],"address":"钦北区人民法院大寺人民法庭","poiweight":"0.113731","name":"大寺工商所党建工作指导站","location":"108.453505,22.133740","distance":"465.613","tel":[],"type":"政府机构及社会团体;工商税务机构;工商部门"},{"id":"B0FFLBXEOK","direction":"东","businessarea":[],"address":"X300与望海大道交叉路口往东约290米","poiweight":"0.113731","name":"大寺工商所","location":"108.453509,22.133740","distance":"466.002","tel":[],"type":"政府机构及社会团体;工商税务机构;工商部门"},{"id":"B030900MR8","direction":"南","businessarea":[],"address":"218省道西50米","poiweight":"0.287237","name":"大寺市场","location":"108.449006,22.128945","distance":"424.486","tel":[],"type":"购物服务;综合市场;综合市场"}]}
     * info : OK
     * infocode : 10000
     */

    private String status;
    private RegeocodeBean regeocode;
    private String info;
    private String infocode;

    @Data
    public static class RegeocodeBean implements Serializable {
        /**
         * roads : [{"id":"0777F49F023004130","location":"108.448,22.1323","direction":"东","name":"解放路","distance":"131.03"},{"id":"0777F49F023004600037","location":"108.451,22.1332","direction":"西","name":"望海大道","distance":"162.318"},{"id":"0777F49F02200326","location":"108.448,22.1334","direction":"东","name":"300县道","distance":"171.835"}]
         * roadinters : [{"second_name":"解放路","first_id":"0777F49F02200326","second_id":"0777F49F023004130","location":"108.447573,22.133362","distance":"171.835","first_name":"300县道","direction":"东"}]
         * formatted_address : 广西壮族自治区钦州市钦北区大寺镇钦北区
         * addressComponent : {"city":"钦州市","province":"广西壮族自治区","adcode":"450703","district":"钦北区","towncode":"450703110000","streetNumber":{"number":"4号","location":"108.448898,22.132871","direction":"西北","distance":"25.0248","street":"解放路"},"country":"中国","township":"大寺镇","businessAreas":[[]],"building":{"name":[],"type":[]},"neighborhood":{"name":[],"type":[]},"citycode":"0777"}
         * aois : []
         * pois : [{"id":"B0309001EJ","direction":"西南","businessarea":[],"address":"大寺镇解放西路73号","poiweight":"0.373842","name":"大寺中心小学","location":"108.447669,22.130226","distance":"318.589","tel":"0777-5718510","type":"科教文化服务;学校;小学"},{"id":"B030900MTG","direction":"西南","businessarea":[],"address":"218省道与300县道交叉口东南200米","poiweight":"0.087371","name":"大寺村委卫生所","location":"108.448226,22.132099","distance":"117.099","tel":[],"type":"医疗保健服务;诊所;诊所"},{"id":"B0FFHUINNN","direction":"西北","businessarea":[],"address":"大寺镇安发大街34号","poiweight":"0.178596","name":"应钟幼儿园","location":"108.448727,22.133278","distance":"69.7229","tel":[],"type":"科教文化服务;学校;幼儿园"},{"id":"B030900DZZ","direction":"Center","businessarea":[],"address":"钦北区","poiweight":"0.600366","name":"钦北区","location":"108.449110,22.132761","distance":"0","tel":[],"type":"地名地址信息;普通地名;区县级地名"},{"id":"B0FFGUO614","direction":"东南","businessarea":[],"address":[],"poiweight":"0.0618232","name":"祥盛小区","location":"108.450131,22.131548","distance":"171.052","tel":[],"type":"商务住宅;住宅区;住宅小区"},{"id":"B0FFFZE2KC","direction":"东北","businessarea":[],"address":"218省道东100米","poiweight":"0.129781","name":"万宁床垫","location":"108.451152,22.133668","distance":"233.261","tel":"13977785380;13977739586","type":"购物服务;家居建材市场;家具城"},{"id":"B0FFHEWWWQ","direction":"西","businessarea":[],"address":"218省道与300县道交叉口南50米","poiweight":"0.182294","name":"大寺供销社鑫华幼儿园","location":"108.447285,22.133121","distance":"192.201","tel":"0777-5710000;0777-5719999","type":"科教文化服务;学校;幼儿园"},{"id":"B0FFF2Y4NC","direction":"东南","businessarea":[],"address":"福旺镇六中路口","poiweight":"0.183522","name":"幸福幼儿园","location":"108.451489,22.130314","distance":"366.198","tel":"0777-5725388;13977771677","type":"科教文化服务;学校;幼儿园"},{"id":"B0FFL1CX25","direction":"东","businessarea":[],"address":"望海大道(幸福幼儿园北侧约200米)","poiweight":"0.195672","name":"童之梦幼儿园","location":"108.451097,22.132501","distance":"206.699","tel":[],"type":"科教文化服务;学校;幼儿园"},{"id":"B0FFFZDUVB","direction":"西北","businessarea":[],"address":"安达西大街33号","poiweight":"0.205382","name":"锦筵大酒店","location":"108.448670,22.133586","distance":"102.325","tel":"0777-5713333;0777-5715555","type":"住宿服务;宾馆酒店;宾馆酒店"},{"id":"B0FFH6QL1S","direction":"北","businessarea":[],"address":"大寺镇镇北3路87号","poiweight":"0.182388","name":"大风车幼儿园","location":"108.448559,22.134755","distance":"228.872","tel":"13877729993","type":"科教文化服务;学校;幼儿园"},{"id":"B03090MAVW","direction":"东北","businessarea":[],"address":"大寺镇望海大道91号","poiweight":"0.24998","name":"钦州市钦北区大寺第二中学","location":"108.450583,22.136043","distance":"395.238","tel":[],"type":"科教文化服务;学校;中学"},{"id":"B0FFLCVABD","direction":"东","businessarea":[],"address":"望海大道(大寺兴华市场南侧约100米)","poiweight":"0.176325","name":"国发医药(大寺店)","location":"108.450962,22.133156","distance":"195.753","tel":[],"type":"医疗保健服务;医药保健销售店;药房"},{"id":"B0FFLCWNRT","direction":"西南","businessarea":[],"address":"大寺镇218省道与311省道交叉口东200米","poiweight":"0.147192","name":"顺发大排档","location":"108.447963,22.131220","distance":"208.149","tel":"0777-5719628","type":"餐饮服务;餐饮相关场所;餐饮相关"},{"id":"B0FFFZE0T4","direction":"北","businessarea":[],"address":"大寺镇望海大道","poiweight":"0.155107","name":"廖康药店","location":"108.449844,22.135283","distance":"290.45","tel":[],"type":"医疗保健服务;医药保健销售店;药房"},{"id":"B0FFF5W2M2","direction":"南","businessarea":[],"address":"三沿路33号","poiweight":"0.219819","name":"中国信合(镇中分社)","location":"108.450398,22.128580","distance":"483.5","tel":"0777-5718351;0777-5718462","type":"金融保险服务;银行;农村商业银行"},{"id":"B0FFLCPSKH","direction":"南","businessarea":[],"address":"解放路(大寺市场东北侧约100米)","poiweight":"0.204403","name":"功德楼","location":"108.450118,22.129632","distance":"363.126","tel":[],"type":"商务住宅;楼宇;楼宇相关"},{"id":"B0FFF5W2LY","direction":"南","businessarea":[],"address":"大寺镇解放西路4号","poiweight":"0.243017","name":"中国农业银行(钦州大寺支行)","location":"108.450557,22.128330","distance":"514.788","tel":"0777-5718351","type":"金融保险服务;银行;中国农业银行"},{"id":"B030900MR9","direction":"东北","businessarea":[],"address":"大寺镇望海大道215号","poiweight":"0.108275","name":"大寺兴华市场","location":"108.451120,22.134793","distance":"306.464","tel":[],"type":"购物服务;综合市场;综合市场"},{"id":"B03090MNH0","direction":"南","businessarea":[],"address":"大寺镇解放路","poiweight":"0.308906","name":"平康药店","location":"108.448617,22.129630","distance":"351.864","tel":[],"type":"医疗保健服务;医药保健销售店;药房"},{"id":"B0FFGM3QVP","direction":"南","businessarea":[],"address":"旧圩江南市场河边木棉花","poiweight":"0.476845","name":"北帝庙","location":"108.450437,22.129451","distance":"392.632","tel":[],"type":"风景名胜;风景名胜;寺庙道观"},{"id":"B0FFLCQ4CB","direction":"东南","businessarea":[],"address":[],"poiweight":"0.278199","name":"北帝庙综合楼","location":"108.450613,22.129514","distance":"392.884","tel":[],"type":"风景名胜;风景名胜;寺庙道观"},{"id":"B0FFFZAJWO","direction":"南","businessarea":[],"address":"中山路西50米","poiweight":"0.200468","name":"钦北利民印刷厂","location":"108.447916,22.129293","distance":"404.782","tel":"0777-5718365;13877758336","type":"公司企业;工厂;工厂"},{"id":"B0FFFZDUWP","direction":"东南","businessarea":[],"address":"218省道东50米","poiweight":"0.18074","name":"育苗幼儿园","location":"108.452750,22.129293","distance":"537.875","tel":[],"type":"科教文化服务;学校;幼儿园"},{"id":"B030900MTY","direction":"北","businessarea":[],"address":"望海大道","poiweight":"0.48","name":"大寺客运站","location":"108.450231,22.138143","distance":"609.512","tel":[],"type":"交通设施服务;长途汽车站;长途汽车站"},{"id":"B0FFLBY5U0","direction":"东","businessarea":[],"address":"钦北区人民法院大寺人民法庭","poiweight":"0.204979","name":"钦北区人民法院壮语巡回法庭","location":"108.453245,22.133719","distance":"439.043","tel":[],"type":"政府机构及社会团体;公检法机构;法院"},{"id":"B0FFFZADIW","direction":"东南","businessarea":[],"address":"解放路(大寺市场东南侧约250米)","poiweight":"0.0674643","name":"钦北区人民检察院大寺检查室","location":"108.451355,22.127772","distance":"601.062","tel":[],"type":"政府机构及社会团体;公检法机构;检察院"},{"id":"B0FFLBXYZD","direction":"东","businessarea":[],"address":"钦北区人民法院大寺人民法庭","poiweight":"0.113731","name":"大寺工商所党建工作指导站","location":"108.453505,22.133740","distance":"465.613","tel":[],"type":"政府机构及社会团体;工商税务机构;工商部门"},{"id":"B0FFLBXEOK","direction":"东","businessarea":[],"address":"X300与望海大道交叉路口往东约290米","poiweight":"0.113731","name":"大寺工商所","location":"108.453509,22.133740","distance":"466.002","tel":[],"type":"政府机构及社会团体;工商税务机构;工商部门"},{"id":"B030900MR8","direction":"南","businessarea":[],"address":"218省道西50米","poiweight":"0.287237","name":"大寺市场","location":"108.449006,22.128945","distance":"424.486","tel":[],"type":"购物服务;综合市场;综合市场"}]
         */

        private String formatted_address;
        private AddressComponentBean addressComponent;
        private List<RoadsBean> roads;
        private List<RoadintersBean> roadinters;
        private List<?> aois;
        private List<PoisBean> pois;

        @Data
        public static class AddressComponentBean implements Serializable {
            /**
             * city : 钦州市
             * province : 广西壮族自治区
             * adcode : 450703
             * district : 钦北区
             * towncode : 450703110000
             * streetNumber : {"number":"4号","location":"108.448898,22.132871","direction":"西北","distance":"25.0248","street":"解放路"}
             * country : 中国
             * township : 大寺镇
             * businessAreas : [[]]
             * building : {"name":[],"type":[]}
             * neighborhood : {"name":[],"type":[]}
             * citycode : 0777
             */

            private String city;
            private String province;
            private String adcode;
            private String district;
            private String towncode;
            private StreetNumberBean streetNumber;
            private String country;
            private String township;
            private BuildingBean building;
            private NeighborhoodBean neighborhood;
            private String citycode;
            private List<businessAreasBean> businessAreas;

            @Data
            public static class businessAreasBean implements Serializable {
                private String location;
                private String name;
                private String id;
            }

            @Data
            public static class StreetNumberBean implements Serializable {
                /**
                 * number : 4号
                 * location : 108.448898,22.132871
                 * direction : 西北
                 * distance : 25.0248
                 * street : 解放路
                 */

                private String number;
                private String location;
                private String direction;
                private String distance;
                private String street;
            }

            @Data
            public static class BuildingBean implements Serializable {
                private String name;
                private String type;
            }

            @Data
            public static class NeighborhoodBean implements Serializable {
                private String name;
                private String type;
            }
        }

        @Data
        public static class RoadsBean implements Serializable {
            /**
             * id : 0777F49F023004130
             * location : 108.448,22.1323
             * direction : 东
             * name : 解放路
             * distance : 131.03
             */

            private String id;
            private String location;
            private String direction;
            private String name;
            private String distance;
        }

        @Data
        public static class RoadintersBean implements Serializable {
            /**
             * second_name : 解放路
             * first_id : 0777F49F02200326
             * second_id : 0777F49F023004130
             * location : 108.447573,22.133362
             * distance : 171.835
             * first_name : 300县道
             * direction : 东
             */

            private String second_name;
            private String first_id;
            private String second_id;
            private String location;
            private String distance;
            private String first_name;
            private String direction;
        }

        @Data
        public static class PoisBean implements Serializable {
            /**
             * id : B0309001EJ
             * direction : 西南
             * businessarea : []
             * address : 大寺镇解放西路73号
             * poiweight : 0.373842
             * name : 大寺中心小学
             * location : 108.447669,22.130226
             * distance : 318.589
             * tel : 0777-5718510
             * type : 科教文化服务;学校;小学
             */

            private String id;
            private String direction;
            private String address;
            private String poiweight;
            private String name;
            private String location;
            private String distance;
            private String tel;
            private String type;
            private List<?> businessarea;
        }
    }
}
