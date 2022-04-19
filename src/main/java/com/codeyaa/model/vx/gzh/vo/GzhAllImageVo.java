package com.codeyaa.model.vx.gzh.vo;

import lombok.Data;

import java.util.List;

@Data
public class GzhAllImageVo {
    private Long total_count;
    private Long item_count;
    private List<ItemBean> item;

    @Data
    public static class ItemBean {
        private String media_id;
        private String name;
        private Long update_time;
        private String url;
        private List<?> tags;
    }
}
