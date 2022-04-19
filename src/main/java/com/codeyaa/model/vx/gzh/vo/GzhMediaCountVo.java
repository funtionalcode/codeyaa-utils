package com.codeyaa.model.vx.gzh.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GzhMediaCountVo {
    @SerializedName("voice_count")
    private Double voiceCount;
    @SerializedName("video_count")
    private Double videoCount;
    @SerializedName("image_count")
    private Double imageCount;
    @SerializedName("news_count")
    private Double newsCount;

    public GzhMediaCountVo(Double voiceCount, Double videoCount, Double imageCount, Double newsCount) {
        this.voiceCount = voiceCount;
        this.videoCount = videoCount;
        this.imageCount = imageCount;
        this.newsCount = newsCount;
    }
}
