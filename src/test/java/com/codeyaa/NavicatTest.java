package com.codeyaa;

import com.codeyaa.model.navicat.vo.PasswordVo;
import com.codeyaa.utils.common.NavicatDecoder;

import java.io.File;
import java.util.List;


/**
 * Created on 2022/6/19.
 *
 * @author zyg
 */
public class NavicatTest {
    public static void main(String[] args) {
        decoder();
    }

    private static void decoder() {
        NavicatDecoder navicatDecoder = new NavicatDecoder(12);
        List<PasswordVo> ps = navicatDecoder.decryptTwelve(new File("C:\\Users\\Administrator\\Desktop\\connections.ncx"));
        ps.forEach(System.out::println);
    }
}
