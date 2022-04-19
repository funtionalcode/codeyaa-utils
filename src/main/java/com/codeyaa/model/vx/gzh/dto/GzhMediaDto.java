package com.codeyaa.model.vx.gzh.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GzhMediaDto {
    @SerializedName("media_id")
    private String mediaId;

    public GzhMediaDto(String mediaId) {
        this.mediaId = mediaId;
    }
}
