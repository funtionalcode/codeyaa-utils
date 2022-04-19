package com.codeyaa.model.aliyun.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Funtionalcode
 * @className AccessKey
 * @description 阿里云密钥实体类
 * @date 2021/6/15 11:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliYunAccessKeyDto {
    private String accessKeyId;
    private String accessKeySecret;
}
