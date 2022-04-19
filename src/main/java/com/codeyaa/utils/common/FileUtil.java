package com.codeyaa.utils.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Funtionalcode
 * @className FileUtil
 * @description 文件工具类
 * @date 2021/5/27 12:10
 */
public class FileUtil {
    /**
     * @param file
     * @Description: 读取文件二进制byte[]
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/31 15:50
     */
    public static byte[] getFilePathBytes(String file) {
        try {
            File f = new File(file);
            int length = (int) f.length();
            return new byte[length];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * show 文件数组转换为字节数组.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param files
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/21 3:21
     */
    public static List<Byte[]> getFilesBytes(File[] files) {
        try {
            ArrayList<Byte[]> bytes = new ArrayList<>();
            for (File file : files) {
                int length = (int) file.length();
                bytes.add(new Byte[length]);
            }
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param filePath 文件绝对路径
     * @Description: 读取文件返回字符串
     * @return: 文件字符串（\r\n换行）
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/31 16:17
     */
    public static String readFile(String filePath) {
        FileInputStream fis = null;
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(filePath);
            fr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != fr) {
                    fr.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String readFile(File file) {
        FileInputStream fis = null;
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(file);
            fr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != fr) {
                    fr.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 读取 InputStream 到 String字符串中
     */
    public static String readStream(InputStream in) {
        try {
            //<1>创建字节数组输出流，用来输出读取到的内容
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //<2>创建缓存大小
            byte[] buffer = new byte[1024]; // 1KB
            //每次读取到内容的长度
            int len = -1;
            //<3>开始读取输入流中的内容
            while ((len = in.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
                baos.write(buffer, 0, len);   //把读取到的内容写到输出流中
            }
            //<4> 把字节数组转换为字符串
            String content = baos.toString();
            //<5>关闭输入流和输出流
            in.close();
            baos.close();
            //<6>返回字符串结果
            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * @param sourceStr  待写入的字符串
     * @param targetPath 目标文件绝对路径
     * @param splits     换行字符
     * @Description: 字符串写入文件
     * @return:
     * @Author Funtionalcode
     * @Email: 1103005123@qq.com
     * @Date: 2021/5/31 16:18
     */
    public static void copyFile(String sourceStr, String targetPath, String splits) {
        FileWriter fw_o = null;
        BufferedWriter bw = null;

        try {
            fw_o = new FileWriter(targetPath);
            bw = new BufferedWriter(fw_o);
            if (StringUtils.isBlank(splits)) {
                bw.write(sourceStr + "\r\n");
                bw.flush();
                return;
            }
            String[] splitFiles = sourceStr.split(splits);
            for (int i = 0; i < splitFiles.length; i++) {
                bw.write(splitFiles[i] + "\r\n");
                if (i % 1000 == 0) {
                    bw.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bw) {
                    bw.close();
                }
                if (null != fw_o) {
                    fw_o.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String replayFile(String filePath) {
        FileInputStream fis = null;
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(filePath);
            fr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != fr) {
                    fr.close();
                }
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String replayXmlState(String regex, String target, String content) {
        String replaceAll = content.replaceAll(regex, target);
        return replaceAll;
    }

    /**
     * show 文本截取匹配正则的内容.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param regex
     * @param content
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/21 3:18
     */
    public static List<String> getRegexString(String regex, String content) {
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(regex);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(content);
        ArrayList<String> regexs = new ArrayList<>();
        //此处find（）每次被调用后，会偏移到下一个匹配
        while (m.find()) {
            regexs.add(m.group());
        }
        return regexs;
    }

    /**
     * show XML 文本转换为 Java 实体类.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param xml XML文本
     * @param t   转换目标类
     * @return
     * @author xin11.xin
     * @date 2021/8/21 3:14
     * @singe 1.0
     */
    public static <T> T convertToXMLBean(String xml, Class<T> t) {
        T obj = null;
        try {
            JAXBContext context = JAXBContext.newInstance(t);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            obj = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * show Java 实体类转换为输出流.
     * <p>show 方法的详细说明第一行<br> * show 方法的详细说明第二行
     *
     * @param bean
     * @param t
     * @param outputStream
     * @Return
     * @Author xin11.xin
     * @Date 2021/8/21 3:18
     */
    public static <T> T convertToXMLStr(T bean, Class<T> t, OutputStream outputStream) {
        try {
            JAXBContext context = JAXBContext.newInstance(t);

            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(bean, outputStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static String getTargetFullPath(Class clazz) {
        String path = clazz.getResource("/").getPath().substring(1);
        String name = "target";
        return path.substring(0, path.lastIndexOf(name) + name.length());
    }
    public static File autoCreate(File file){
        return null;

    }
}
