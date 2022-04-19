package com.codeyaa.model.vx.gzh.vo;

import com.codeyaa.model.vx.gzh.GzhMediaBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImgListModel extends GzhMediaBase implements Serializable {
    /**
     * total_count : TOTAL_COUNT
     * item_count : ITEM_COUNT
     * item : [{"media_id":"MEDIA_ID","name":"NAME","update_time":"UPDATE_TIME","url":"URL"}]
     */

    private Integer total_count;
    private Integer item_count;
    private List<ItemBean> item;

    @Data
    public static class ItemBean implements Serializable {
        /**
         * media_id : MEDIA_ID
         * name : NAME
         * update_time : UPDATE_TIME
         * url : URL
         */

        private String media_id;
        private String name;
        private String update_time;
        private String url;
    }
}
