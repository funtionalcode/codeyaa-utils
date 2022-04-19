package com.codeyaa.model.vx.gzh.vo;

import com.codeyaa.model.vx.gzh.GzhMediaBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ManyImgGzhMedia extends GzhMediaBase {
    private String name;
    private String mediaId;
    private String url;


}
