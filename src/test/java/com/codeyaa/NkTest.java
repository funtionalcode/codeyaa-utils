package com.codeyaa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created on 2022/7/4.
 *
 * @author zyg
 */
public class NkTest {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = br.readLine();
            int len = str.length();
            int start = 0;
            while (len >= 8) {
                System.out.println(str.substring(start, start + 8));
                start += 8;
                len -= 8;
            }
            if (len > 0) {
                char[] chars = new char[8 - len];
                Arrays.fill(chars, '0');
                System.out.print(String.format("%s%s", str.substring(start),
                        String.valueOf(chars)));
            }
        } while (null != str);
    }
}
