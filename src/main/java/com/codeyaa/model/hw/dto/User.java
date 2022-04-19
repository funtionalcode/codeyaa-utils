package com.codeyaa.model.hw.dto;


import lombok.Data;

/**
 * @author Funtionalcode
 * @className User
 * @description 华为发送获取 token 请求实体类
 * @date 2021/5/27 12:09
 */
@Data
public class User {
    Domain domain;
    String name;
    String password;
}
