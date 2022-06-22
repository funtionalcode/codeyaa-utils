package com.codeyaa.bitpic;

import com.codeyaa.utils.common.BitPic;
import com.codeyaa.utils.common.FileUtil;
import com.codeyaa.utils.common.MD5Util;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * Created on 2022/6/21.
 *
 * @author zyg
 */
public class BitPicTest {
    @SneakyThrows
    public static void main(String[] args) {
        String iPath = BitPic.class.getClassLoader().getResource("").getPath() + "tobe.txt";
        String savePath = BitPic.class.getClassLoader().getResource("").getPath() + "saved.png";
        String content = BitPic.readImageChar(BitPic.class.getClassLoader().getResource("quiz5-tobe.png").getPath());

//        FileUtils.write(new File(iPath),content);
//        content = FileUtils.readFileToString(new File(iPath));
        content = FileUtil.readFile(new File(iPath));

        BitPic.drawTransparent(content,savePath);
        System.out.println(BitPic.readImageChar(savePath));
        System.out.println();
        String abcContent = BitPic.readImageChar(BitPic.class.getClassLoader().getResource("quiz5-abc.png").getPath());
        System.out.println(abcContent);
        System.out.println(MD5Util.encode(abcContent));
    }
}
