package com.codeyaa.utils.tripartite.excel;

import com.codeyaa.utils.common.StringUtils;
import com.codeyaa.utils.common.reflection.UnSafeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PoiUtil {
    public static <T> List<T> convertExcelBeans(InputStream inputStream, String name, Class<T> clazz) {
        List<T> excelBeans = new ArrayList<>();
        // 指定excel文件，创建缓存输入流
        Workbook workbook = null;
        try {
            // 直接传入输入流即可，此时excel就已经解析了
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            try {
                workbook = new HSSFWorkbook(inputStream);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        // 第一列文本格式
        // 选择要处理的sheet名称
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        // 迭代遍历sheet剩余的每一行
        rowFor:
        for (int rowNum = 0; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            // 读取第一行（表头）
            Cell usernameCell = row.getCell(0);
            if (rowNum == 0) {
                String username = dataFormatter.formatCellValue(usernameCell);
                String hashrate24h = dataFormatter.formatCellValue(row.getCell(1));
                String money = dataFormatter.formatCellValue(row.getCell(2));
                log.info("表头 --> {}\t{}\t{}", username, hashrate24h, money);
            }
            // 非表头（注意读取的时候要注意单元格内数据的格式，要使用正确的读取方法）
            else {
                int cellNum = row.getLastCellNum();
                T t = UnSafeUtil.allocateInstance(clazz);
                Field[] allFields = clazz.getDeclaredFields();

                for (int i = 0; i < cellNum; i++) {
                    Field field = allFields[i];
                    field.setAccessible(true);
                    String val = dataFormatter.formatCellValue(row.getCell(i));
                    if (StringUtils.isBlank(val)) {
                        continue rowFor;
                    }
                    String typeName = field.getType().getSimpleName();
                    switch (typeName) {
                        case "String":
                            UnSafeUtil.setField(t, field, val);
                            break;
                        case "BigDecimal":
                            UnSafeUtil.setField(t, field, new BigDecimal(val));
                            break;
                        default:
                            break;
                    }
                }
                excelBeans.add(t);
            }
        }
        return excelBeans;
    }
}
