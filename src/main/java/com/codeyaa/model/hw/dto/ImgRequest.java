package com.codeyaa.model.hw.dto;


import lombok.Data;

/**
 * @author Funtionalcode
 * @className ImgRequest
 * @description 华为发送获取 token 请求实体类
 * @date 2021/5/27 12:09
 */
@Data
public class ImgRequest {
    String image;
    boolean detect_direction;
}
