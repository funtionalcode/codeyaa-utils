package com.codeyaa.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
/**
 * @className XiuMiException
 * @description 秀米异常类
 * @date 2021/5/27 12:07
 * @author Funtionalcode
 */
public class XiuMiException extends RuntimeException {
    private static final long serialVersionUID = 4106926523748533395L;
    String msg;

    public XiuMiException(String msg) {
        super(" 错误信息：" + msg);
        this.msg = msg;
    }
}