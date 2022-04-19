package com.codeyaa;// This file is auto-generated, don't edit it. Thanks.

import com.codeyaa.model.aliyun.dto.AliYunAccessKeyDto;
import com.codeyaa.utils.tripartite.AliYunUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AliYunTest {
    private static AliYunUtil aliYunUtil;

    static {
//        String fileName = "d:\\alysk\\LZYAccessKey.csv"; // lzy
        String fileName = "c:\\cert\\alisk\\QPAccessKey.csv"; // qp
        AliYunAccessKeyDto as = null;
        try {
            as = Files.lines(Paths.get(fileName))
                    .filter(line -> !line.contains("AccessKey ID,AccessKey Secret") && !line.contains("AccessKeyId,AccessKeySecret"))
                    .map(line -> line.split(","))
                    .map(line -> new AliYunAccessKeyDto(line[0], line[1])).findFirst()
                    .orElseThrow(() -> new RuntimeException("阿里云密钥获取异常"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        aliYunUtil = new AliYunUtil(as.getAccessKeyId(), as.getAccessKeySecret());
        aliYunUtil.setEndpoint("ecs.aliyuncs.com");
//        aliYunUtil.setSecurityGroupId("sg-2ze497ru6iyk02y341nf"); // lzy
        aliYunUtil.setSecurityGroupId("sg-2ze85m9yb0gb5f49bz6o"); // qp
        aliYunUtil.setSourceCidrIp("0.0.0.0/0");
        aliYunUtil.setIpProtocol("tcp");
        aliYunUtil.setRegionId("cn-beijing");
    }

    public static void main(String[] args_) throws Exception {
//        authPort();
        List<String> rules = aliYunUtil.describeSecurityGroupAttribute();
        rules.forEach(System.out::println);
    }

    private static void authPort() {
        String ports = "frp-manager: 7500\n";
//        String ports = "frps: 7000-7010\n";
        List<HashMap<String, List>> port = analysisPort(ports);
        for (HashMap<String, List> map : port) {
            addPort(map);
        }
    }

    private static List<HashMap<String, List>> analysisPort(String portStr) {
        String[] portArr = portStr
                .replaceAll("(: )|(:)", "：").split("\n");
        ArrayList<String> portList = new ArrayList<>(Arrays.asList(portArr));
        List<HashMap<String, List>> port = portList.stream().map(row -> {
            HashMap<String, List> portMap = new HashMap<>();
            int index = row.indexOf("：");
            ArrayList<String> list = new ArrayList<>();
            list.add(row.substring(index).replace("：", "").trim());
            list.add(row.substring(0, index));
            portMap.put("port", list);
            return portMap;
        }).collect(Collectors.toList());
        return port;
    }

    @SneakyThrows
    private static List<String> addPort(Map port) {
        List ports = (List) port.get("port");
        String one = ports.get(0).toString();
        String two = ports.get(1).toString();
        if (one.contains("-")) {
            List<String> portList = Arrays.stream(one.split("-"))
                    .map(String::trim)
                    .collect(Collectors.toList());
            aliYunUtil.setPortRange(String.format("%s/%s", portList.get(0), portList.get(1)));
        } else {
            aliYunUtil.setPortRange(String.format("%s/%s", one, one));

        }
        aliYunUtil.setNicType("internet");
        aliYunUtil.setPolicy("accept");
        aliYunUtil.setDescription(two);

        String s = aliYunUtil.authorizeSecurityGroup();
        List<String> rules = aliYunUtil.describeSecurityGroupAttribute();
        rules.add(s);
        return rules;
    }
}
