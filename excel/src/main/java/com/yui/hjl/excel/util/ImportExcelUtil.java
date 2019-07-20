package com.yui.hjl.excel.util;

import com.yui.hjl.excel.annotation.ImportExcel;
import com.yui.hjl.excel.dao.Human;
import com.yui.hjl.excel.domain.ExcelData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * excel 导入
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
public class ImportExcelUtil {

    public static <T> List<T> importExcel(Class<T> t, File file, int startRow, String sheetName){
        List<T> array = new ArrayList<>(16);
        for (Field declaredField : t.getDeclaredFields()) {
            if (!declaredField.isAnnotationPresent(ImportExcel.class)) {
                continue;
            }
            final ImportExcel annotation = declaredField.getAnnotation(ImportExcel.class);
            System.out.println(annotation.column());
        }

        return null;
    }
    public static void main(String[] args) {
        final List<Human> humans = importExcel(Human.class, null, 0, "1");
//        File file = new File("file/test/t2.xlsx");
//        ExcelData excelData = new ExcelData(file, 0);
//        excelData.getRow(10);
//        System.out.println(ExcelUtil.getValue(excelData.getCell(2)));
//        System.out.println(ExcelUtil.getValue(excelData.getCell(null, null, 2)));
//        while(excelData.nextSheet() != null){
//            excelData.getRow(1);
//            while (excelData.nextRow() != null){
//                while (excelData.nextCell() != null) {
//                    System.out.print(ExcelUtil.getValue(excelData.getCell()) + "\t");
//                }
//                System.out.println();
//            }
//            System.out.println("===================================================");
//        }
    }

}
