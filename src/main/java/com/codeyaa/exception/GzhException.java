package com.codeyaa.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/**
 * @author DELL
 * @className GzhException
 * @description 公众号异常
 * @date 2021/5/27 12:06
 */
public class GzhException extends RuntimeException {
    private static final long serialVersionUID = -3651029360365996066L;
    String msg;

    public GzhException(String msg) {
        super(" 错误信息：" + msg);
        this.msg = msg;
    }
}