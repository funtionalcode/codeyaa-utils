package com.codeyaa;

import com.codeyaa.utils.common.reflection.BeanUtil;
import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.xml.dto.XmlTestBean;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.codeyaa.utils.common.StringUtils.getRegexString;

public class FileUtilTest {
    public static void main(String[] args) {
        regexMidTest();
    }

    private static void classLoadTest() {
        String path = FileUtilTest.class.getResource("/").getPath().substring(1);
        String name = "target";
        String substring = path.substring(0, path.lastIndexOf(name) + name.length());
        System.out.println(substring);
    }

    private static void readRegex() {
        List<String> s = getRegexString(":(.*):", "8390 : XYK_MX_031 : AGE >= 29 and AGE < 34");
        System.out.println(s.get(0).replaceAll(":|( )+", ""));
    }

    private static void read() {
        String s = FileUtil.readFile("C:\\Users\\codeyaa\\Desktop\\新建文本文档.txt");
        System.out.println("s = " + s);
    }

    private static void getBangsunXml() {
        String s = FileUtil.readFile("C:\\Users\\codeyaa\\Desktop\\1.txt");
        String regex = "(name = .*)";
        List<String> regexString = getRegexString(regex, s);
        regexString.stream().map(item -> {
            String pre = "\"" + item.substring(7, 8).toLowerCase(Locale.ROOT);
            String middle = item.substring(8, item.length() - 1) + "\",";
            return pre + middle;
        }).collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void getBangsunBeanXmlField() {
        List<String> beanNames = BeanUtil.getBeanNames(XmlTestBean.class);
        beanNames.stream().map(item -> {
            String pre = "\"" + item.substring(0, 1).toLowerCase(Locale.ROOT);
            String middle = item.substring(1) + "\",";
            return pre + middle;
        }).collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void replayBangsunXml() {
        String path = "C:\\Users\\codeyaa\\Desktop\\bangsun.xml";
        String s = FileUtil.readFile(path);
        /*// 1
        String node = "PD01CJ02";
        String content = "0.74";
        String node = "PD01AJ02";
        String content = "1";*/
        /*// 2
        String node = "PH010Q02";
        String content = "好哥哥";
        String node = "PH010R01";
        String content = "date";*/
        // 3
        /*String node = "PD01AD01";
        String content = "贷记卡账户";
        String node = "PD01AR01";
        String content = "2000.05.1314:05:04";*/
        /*// 4
        String node = "PD01AJ01";
        String content = "10";
        String node = "PD01BJ01";
        String content = "-8";*/
        // 5
        String node = "PD01ER04";
        String content = "4";
        /*// 6
        String node = "PC02HJ03";
        String content = "11000";*/
        /*// 7
        String node = "PD01AD01";
        String content = "";
        String node = "PD01CD01";
        String content = "";
        String node = "PD01BD01";
        String content = "未结清";
        // 机构
        String node = "PD01AI02";
        String content = "";*/
        /*// 8
        String node = "PD01AD07";
        String content = "";*/
        /*// 9
        String node = "PH010Q03";
        String content = "";*/
        /*// 10
        String node = "PH010Q03";
        String content = "";*/
        /*// test
        String node = "PD01BD01";
        String content = "";*/

        String regex = String.format("(<%s>.*</%s>)", node, node);
        String target = String.format("<%s>%s</%s>", node, content, node);
        List<String> regexString = getRegexString(regex, s);
        regexString.forEach(System.out::println);
        String replayXmlState = FileUtil.replayXmlState(regex, target, s);
        FileUtil.copyFile(replayXmlState, path, "");
        // 删除空行
        String sTmp = FileUtil.readFile(path);
        String regexTmp = "</Document>[\r\n]+";
        String targetTmp = "</Document>";
        String replayXmlStateTmp = FileUtil.replayXmlState(regexTmp, targetTmp, sTmp);
        FileUtil.copyFile(replayXmlStateTmp, path, "");
    }

    private static void createTmpFile() {
        File temp = null;
        try {
            temp = File.createTempFile("testrunoobtmp", ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("文件路径: " + temp.getAbsolutePath());
        temp.deleteOnExit();
    }

    private static void regexMidTest(){
        String content = getRegexString("\\(.*\\)", "未知类型(454564564sda56f)")
                .get(0)
                .replaceAll("\\(|\\)", "");
        System.out.println("content = " + content);
    }
}
