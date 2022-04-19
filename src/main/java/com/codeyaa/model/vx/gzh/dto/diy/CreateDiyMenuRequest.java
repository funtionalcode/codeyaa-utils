package com.codeyaa.model.vx.gzh.dto.diy;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateDiyMenuRequest implements Serializable {

    private static final long serialVersionUID = 1425034744582564539L;
    /**
     * button : [{"type":"click","name":"今日歌曲","key":"V1001_TODAY_MUSIC"},{"name":"菜单","sub_button":[{"type":"view","name":"搜索","url":"http://www.soso.com/"},{"type":"miniprogram","name":"wxa","url":"http://mp.weixin.qq.com","appid":"wx286b93c14bbf93aa","pagepath":"pages/lunar/index"},{"type":"click","name":"赞一下我们","key":"V1001_GOOD"}]}]
     * matchrule : {"tag_id":"2","sex":"1","country":"中国","province":"广东","city":"广州","client_platform_type":"2","language":"zh_CN"}
     */

    private MatchruleBean matchrule;
    private List<ButtonBean> button;

    @Data
    public static class MatchruleBean implements Serializable {
        private static final long serialVersionUID = -6942596635650992452L;
        /**
         * tag_id : 2
         * sex : 1
         * country : 中国
         * province : 广东
         * city : 广州
         * client_platform_type : 2
         * language : zh_CN
         */

        private String tag_id;
        private String sex;
        private String country;
        private String province;
        private String city;
        private String client_platform_type;
        private String language;
    }

    @Data
    public static class ButtonBean implements Serializable {
        private static final long serialVersionUID = 7923008802895640084L;
        /**
         * type : click
         * name : 今日歌曲
         * key : V1001_TODAY_MUSIC
         * sub_button : [{"type":"view","name":"搜索","url":"http://www.soso.com/"},{"type":"miniprogram","name":"wxa","url":"http://mp.weixin.qq.com","appid":"wx286b93c14bbf93aa","pagepath":"pages/lunar/index"},{"type":"click","name":"赞一下我们","key":"V1001_GOOD"}]
         */

        private String type;
        private String name;
        private String key;
        private String url;
        private List<SubButtonBean> sub_button;

        @Data
        public static class SubButtonBean implements Serializable {
            private static final long serialVersionUID = -549389054915828955L;
            /**
             * type : view
             * name : 搜索
             * url : http://www.soso.com/
             * appid : wx286b93c14bbf93aa
             * pagepath : pages/lunar/index
             * key : V1001_GOOD
             */

            private String type;
            private String name;
            private String url;
            private String appid;
            private String pagepath;
            private String key;
        }
    }
}
