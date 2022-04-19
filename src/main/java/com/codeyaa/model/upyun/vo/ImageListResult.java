package com.codeyaa.model.upyun.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImageListResult implements Serializable {

    private String iter;
    private List<FilesBean> files;

    @Data
    public static class FilesBean implements Serializable {

        private Integer last_modified;
        private String type;
        private Integer length;
        private String name;
        private String etag;
    }
}
