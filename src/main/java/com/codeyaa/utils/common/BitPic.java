package com.codeyaa.utils.common;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created on 2022/6/21.
 *
 * @author zyg
 */
public class BitPic {
    private static int width = 256;
    private static int height = 47;

    public static void drawTransparent(String content, String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = content.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            String binaryStr = Integer.toBinaryString(charArray[i]);
            if (binaryStr.length() > 8) {
                stringBuilder.append(String.format("%015d", Long.parseLong(binaryStr)));
            } else {
                stringBuilder.append(String.format("%08d", Long.parseLong(binaryStr)));
            }
        }
        String bits = stringBuilder.toString();

        //创建BufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        // 获取Graphics2D
        Graphics2D g2d = image.createGraphics();

        g2d.dispose();
        g2d = image.createGraphics();

        for (int i = 0, x = 0, y = 0; i < bits.length(); i++) {
            char c = bits.charAt(i);
            if (c == '0') {
                g2d.setColor(new Color(255, 255, 255));
            } else {
                g2d.setColor(new Color(0, 0, 0));
            }
            if (x + 1 > width) {
                y++;
                x = 0;
            }
            if (y == height) {
                break;
            }
            g2d.fillRect(x++, y, 1, 1);
        }
        // 释放对象
        g2d.dispose();
        // 保存文件
        ImageIO.write(image, "png", new File(filePath));
    }

    public static String readImageChar(String path) {
        try {
            BufferedImage bimg = ImageIO.read(new File(path));
            Raster raster = bimg.getData();
            int width = raster.getWidth();
            int height = raster.getHeight();
            int numBands = raster.getNumBands();
            int[] temp = new int[width * height * numBands];
            int[] pixels = raster.getPixels(0, 0, width, height, temp);
            ArrayList<String> bits = new ArrayList<>();
            StringBuilder oneBit = new StringBuilder();
            int length = pixels.length;
            for (int i = 0; i < length; i += numBands) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(pixels[i]).append(pixels[i + 1]).append(pixels[i + 2]);

                String currentBit = Objects.equals(stringBuilder.toString(), "255255255") ? "0" : "1";

                oneBit.append(currentBit);

                if (oneBit.indexOf("0") == 0 && oneBit.length() == 8) {
                    bits.add(oneBit.toString());
                    oneBit = new StringBuilder();
                }
                if (oneBit.indexOf("1") == 0 && oneBit.length() == 15) {
                    bits.add(oneBit.toString());
                    oneBit = new StringBuilder();
                }
            }
            bits.removeIf(row -> row.equals(String.format("%08d", 0L)) || row.equals("111111111111111"));
            return toString(String.join(" ", bits));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static char BinstrToChar(String binStr) {
        int[] temp = BinstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    public static int[] BinstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }

    public static String toString(String binary) {
        String[] tempStr = binary.split(" ");
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = BinstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }
}
