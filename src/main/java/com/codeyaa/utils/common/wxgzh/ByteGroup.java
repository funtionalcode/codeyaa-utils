package com.codeyaa.utils.common.wxgzh;

import java.util.ArrayList;

/**
 * @author Funtionalcode
 * @className ByteGroup
 * @description 微信工具所需实体类
 * @date 2021/5/27 12:10
 */
public class ByteGroup {
    private ArrayList<Byte> byteContainer = new ArrayList<>();

    public byte[] toBytes() {
        byte[] bytes = new byte[byteContainer.size()];
        for (int i = 0; i < byteContainer.size(); i++) {
            bytes[i] = byteContainer.get(i);
        }
        return bytes;
    }

    public ByteGroup addBytes(byte[] bytes) {
        for (byte b : bytes) {
            byteContainer.add(b);
        }
        return this;
    }

    public int size() {
        return byteContainer.size();
    }
}
