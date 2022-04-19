package com.codeyaa.model.hw.dto;


import lombok.Data;

/**
 * @author Funtionalcode
 * @className Auth
 * @description 华为发送获取 token 请求实体类
 * @date 2021/5/27 12:08
 */
@Data
public class Auth {
    Identity identity;
    Scope scope;
}
