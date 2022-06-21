package com.codeyaa.model.navicat.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2022/6/20.
 *
 * @author zyg
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordVo {
    private String host;
    private String password;
}
