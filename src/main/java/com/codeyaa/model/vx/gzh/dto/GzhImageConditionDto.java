package com.codeyaa.model.vx.gzh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class GzhImageConditionDto {
    private String  type;
    private Long offset;
    private Long count;

    public GzhImageConditionDto(String type, Long offset, Long count) {
        this.type = type;
        this.offset = offset;
        this.count = count;
    }
}
