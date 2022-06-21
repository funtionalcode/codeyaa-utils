package com.codeyaa.utils.common;

import com.codeyaa.model.navicat.vo.PasswordVo;
import com.codeyaa.utils.common.wxgzh.SHA1Util;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 2022/6/19.
 *
 * @author zyg
 */
public class NavicatDecoder {
    private Integer version = 0;
    private final static String AES_KEY = "libcckeylibcckey";
    private final static String AES_IV = "libcciv libcciv ";
    private final static String CONNECT_REGEX = "<Connection.+\"\"/>";
    private final static String HOST_REGEX = "Host=\"[0-9a-zA-Z\\.-]+\"";
    private final static String PASSWORD_REGEX = "Password=\"[0-9A-Z]+\"";

    public NavicatDecoder(Integer version) {
        this.version = version;
    }

    public List<PasswordVo> decryptTwelve(File connections) {
        String fileContent = FileUtil.readFile(connections);
        List<String> connectionList = StringUtils.getRegexString(CONNECT_REGEX, fileContent);
        return connectionList.stream().map(row-> PasswordVo.builder().host(host(row)).password(password(row)).build()).collect(Collectors.toList());
    }

    private String host(String content) {
        List<String> regexString = StringUtils.getRegexString(HOST_REGEX, content);
        if (regexString.isEmpty()) {
            return null;
        }
        String upperString = regexString.get(0).substring(6).replaceAll("\"", "");
        return upperString.toLowerCase();
    }

    private String password(String content) {
        List<String> regexString = StringUtils.getRegexString(PASSWORD_REGEX, content);
        if (regexString.isEmpty()) {
            return null;
        }
        String upperString = regexString.get(0).substring(9).replaceAll("\"", "");
        upperString = upperString.toLowerCase();

        return SHA1Util.opensslDecrypt(upperString, AES_KEY, AES_IV);
    }
}
