package com.codeyaa;

import com.codeyaa.model.aliyun.dto.AliYunAccessKeyDto;
import com.codeyaa.utils.common.reflection.BeanUtil;
import com.codeyaa.utils.common.date.Lunar;

import java.util.List;

public class BeanUtilTest {
    public static void main(String[] args) {
        getClassVal();
    }

    private static void getNames() {
        List<String> beanNames = BeanUtil.getBeanNames(AliYunAccessKeyDto.class);
        System.out.println("beanNames = " + beanNames);
    }

    private static void getValue() {
        AliYunAccessKeyDto aliYunAccessKeyDto = new AliYunAccessKeyDto();
        aliYunAccessKeyDto.setAccessKeyId("`1");
        Object accessKeyId = BeanUtil.getReadValue(aliYunAccessKeyDto, "accessKeyId");
        System.out.println("accessKeyId = " + accessKeyId);
    }

    private static void getClassVal() {
        Object smallFormat = BeanUtil.getReadValue(Lunar.class, "smallFormat");
        System.out.println("smallFormat = " + smallFormat);
    }
}
