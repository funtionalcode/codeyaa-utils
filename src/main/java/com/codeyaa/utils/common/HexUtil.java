package com.codeyaa.utils.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.*;

/**
 * Created on 2022/7/8.
 *
 * @author zyg
 */
public class HexUtil {

    public static String binary2Octal(String binary) {
        return binary2N(binary, 8);
    }

    public static String binary2Hex(String binary) {
        return binary2N(binary, 16);
    }

    public static String octal2Binary(String octal) {
        return n2Binary(octal, 8);
    }

    /**
     * 暂不考虑 负数、小数点
     *
     * @param decimal 十进制
     * @return 二进制
     */
    public static String decimal2Binary(String decimal) {
        StringBuilder tmp = new StringBuilder();

        String[] split = decimal.split("\\.");
        for (int i = 0; i < split.length; i++) {
            long current = Long.parseLong(split[i]);
            if (i == 0) {
                long curTmp = current;
                while (curTmp != 0) {
                    tmp.append(mod(curTmp, 2));
                    curTmp /= 2;
                }
            } else {
                for (int j = 1; j <= current; j++) {
                    // 小数点
                }
            }
        }
        return revert(tmp).toString();
    }

    public static String decimal2Octal(int decimal) {
        return decimal2N(decimal, 8);
    }

    public static String decimal2Hex(int decimal) {
        return decimal2N(decimal, 16);
    }

    public static String hex2Binary(String hex) {
        StringBuilder res = new StringBuilder();
        String[] split = hex.split("\\.");
        for (int i = 0; i < split.length; i++) {
            if (i == 1) {
                res.append(".");
            }
            res.append(n2Binary(split[i], 16));
        }
        return res.toString();
    }

    /**
     * 任意进制转换10进制
     * 不考虑 负数
     *
     * @param n
     * @param index 进制位
     * @return
     */
    private static Double n2Decimal(String n, int index) {
        List<Integer> ascii = Arrays.asList(2, 8, 16);
        if (!ascii.contains(index)) {
            return null;
        }
        double resNum = 0L;
        String[] split = n.split("\\.");
        for (int i = 0; i < split.length; i++) {
            String current = split[i];
            int cLen = current.length();
            if (i == 0) {
                for (int j = 0; j < cLen; j++) {
                    int asciiNum = asciiNum(current.charAt(cLen - j - 1));
                    resNum += asciiNum * pow(index, j);
                }
            } else {
                for (int j = 1; j <= cLen; j++) {
                    int asciiNum = asciiNum(current.charAt(j - 1));
                    resNum += asciiNum * Math.pow(index, -j);
                }
            }

        }
        return resNum;
    }

    private static String binary2N(String binary, int n) {
        List<Integer> ascii = Arrays.asList(8, 16);
        if (StringUtils.isBlank(binary) || !ascii.contains(n)) {
            return null;
        }
        n = NumberUtil.log(2d, n).intValue();
        int length = binary.length();
        int t = binary.contains(".") ? (length - 1) % n : length % n;
        int fill = t == 0 ? 0 : n - t;
        char[] fillChar = new char[fill];
        Arrays.fill(fillChar, '0');
        binary = String.format("%s%s", binary, String.valueOf(fillChar));
        String[] split = binary.split("\\.");
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < split.length; i++) {
            if (i == 1) {
                res.append(".");
            }
            res.append(binary2NInner(split[i], n));
        }

        return res.toString();
    }

    private static StringBuilder binary2NInner(String binary, int n) {
        StringBuilder res = new StringBuilder();
        int start = 0;
        while (start < binary.length()) {
            String mid = binary.substring(start, start + n);
            long innerRes = 0L;
            int mLen = mid.length();
            for (int i = 0; i < mLen; i++) {
                long pow = mid.charAt(i) == '0' ? 0L : Double.valueOf(Math.pow(2, mLen - i - 1)).longValue();
                innerRes += pow;
            }
            start += n;
            if (innerRes <= 9 || innerRes >= 16) {
                res.append(innerRes);
            } else {
                res.append(singleHex(innerRes));
            }
        }
        return res;
    }

    private static String decimal2N(int decimal, int n) {
        List<Integer> ascii = Arrays.asList(2, 8, 16);
        if (!ascii.contains(n)) {
            return null;
        }

        StringBuilder tmp = new StringBuilder();
        for (int i = decimal; i > 0; i /= n) {
            long mod = mod(i, n);
            // 16进制
            if (mod <= 9 || mod >= 16) {
                tmp.append(mod);
            } else {
                tmp.append(singleHex(mod));
            }
        }
        // 反转
        StringBuilder res = revert(tmp);

        return res.toString();

    }

    private static String n2Binary(String n, int index) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n.length(); i++) {
            res.append(n2Binary(n.charAt(i), index));
        }
        return res.toString();
    }

    private static String n2Binary(char n, int index) {
        List<Integer> ascii = Arrays.asList(8, 16);
        if (!ascii.contains(index)) {
            return null;
        }
        index = NumberUtil.log(2d, index).intValue();
        int num = asciiNum(n);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < index; i++) {
            int asc = (num >> index - i - 1) & 1;
            res.append(asc);
        }
        return res.toString();
    }

    /**
     * a % 2^n = a & (2^n-1)
     *
     * @param source 被求余
     * @param mod
     * @return
     */
    private static long mod(long source, int mod) {
        return source & mod - 1;
    }

    /**
     * (int)  0 --- 9  --> 48 - 57
     * (int) 'A' - 'Z' --> 65 - 90
     * (int) 'a' - 'z' --> 97 - 122
     * 字母转大写
     *
     * @param n
     * @return
     */
    private static int asciiNum(int n) {
        return n <= '9' ? n - '0' : n > 'Z' ? n - '0' - 32 - 7 : n - '0' - 7;
    }

    private static Character singleHex(Long c) {
        char[] hexChars = {'A', 'B', 'C', 'D', 'E', 'F'};
        return hexChars[(int) (c - 10)];
    }

    /**
     * 暂不支持负数
     *
     * @param source
     * @param pow    次方
     * @return
     */
    public static long pow(long source, long pow) {
        long sum = 1L;
        long tmp = source;
        while (pow != 0L) {
            // 奇数
            if ((pow & 1) == 1) {
                sum *= tmp;
            }
            tmp *= tmp;
            pow >>= 1;
        }
        return sum;
    }

    private static StringBuilder revert(StringBuilder source) {
        StringBuilder res = new StringBuilder();
        int len = source.length();
        for (int i = 0; i < len; i++) {
            res.append(source.charAt(len - i - 1));
        }
        return res;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(Byte.valueOf("1").equals((byte)1));

//        System.out.println(n2Decimal("1101.01", 2));
//        System.out.println(n2Decimal("1234", 8));
//        System.out.println(n2Decimal("AA", 16));
//        System.out.println(octal2Binary("1674"));
//        System.out.println(hex2Binary("3A7.B1"));
//        System.out.println(binary2Octal("100101110111.0111"));
//        System.out.println(binary2Hex("01100001.1110"));
//        System.out.println(decimal2N(10, 2));
//        System.out.println(decimal2Octal(36926));
//        System.out.println(decimal2Hex(935));
//        System.out.println(decimal2N(1234, 16));
//        System.out.println(decimal2N(10, 32));
//        System.out.println(decimal2Binary("100"));

    }
}
