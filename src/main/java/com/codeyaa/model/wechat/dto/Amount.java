package com.codeyaa.model.wechat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className Amount
 * @description TODO(用一句话描述该文件做什么)
 * @date 2021/6/15 00:24
 */
@Data
@NoArgsConstructor
public class Amount {
    private Long total = 1L;
    private String currency = "CNY";
}
