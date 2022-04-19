package com.codeyaa.model.vx.gzh.dto.only;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CreateOnlyMenuRequest implements Serializable {
    private static final long serialVersionUID = -1715164005416988914L;
    private List<ButtonBean> button;

    @Data
    public static class ButtonBean implements Serializable {
        private static final long serialVersionUID = 708205054944951270L;
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
            private static final long serialVersionUID = -3372757214058965081L;
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
