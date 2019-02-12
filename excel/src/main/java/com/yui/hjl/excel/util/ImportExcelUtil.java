package com.yui.hjl.excel.util;

import com.alibaba.fastjson.JSONObject;
import com.yui.hjl.excel.annotation.ImportExcel;
import com.yui.hjl.excel.dao.Human;
import com.yui.hjl.excel.domain.ExcelData;
import com.yui.hjl.excel.runtime.AbstractExcelFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel 导入
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
public class ImportExcelUtil {

    public static <T> List<T> importExcel(Class<T> t, File file, String sheetName) throws Exception {
        List<T> array = new ArrayList<>(16);
        Map<String, ImportExcel> fields = new HashMap<>(16);
        int maxRow = 0;
        int minRow = Integer.MAX_VALUE;
        for (Field declaredField : t.getDeclaredFields()) {
            if (!declaredField.isAnnotationPresent(ImportExcel.class)) {
                continue;
            }
            final ImportExcel annotation = declaredField.getAnnotation(ImportExcel.class);
            fields.put(declaredField.getName(), annotation);
            final int[] row = annotation.row();
            // 获取最大行
            if (row.length == 2 && maxRow != -1){
                if (row[1] == -1 ){
                    maxRow = -1;
                } else if(maxRow < row[1]){
                    maxRow = row[1];
                }
            }
            // 获取最小行
            if (minRow > row[0]){
                minRow = row[0];
            }
        }

        ExcelData excelData = new ExcelData(file, sheetName);
        excelData.getRow(minRow);
        int index = 0;
        while(excelData.nextRow() != null){
            T object = t.getConstructor().newInstance();
            for (Map.Entry<String, ImportExcel> entry : fields.entrySet()) {
                String key = entry.getKey();
                ImportExcel value = entry.getValue();
                // 根据行偏移获取 cell
                final Cell cellUnMove = excelData.getCellUnMove(value.row()[0] + index, value.column());
                // 根据格式获取 value
                final Object cellValue = getValue(value, cellUnMove);
                // 赋值 TODO: try catch 包围, 私有
                final Field declaredField = t.getDeclaredField(key);
                declaredField.setAccessible(true);
                declaredField.set(object, cellValue); 
            }
            array.add(object);
            if (maxRow != -1 && maxRow <= excelData.getRowIndex()){
                break;
            }
            index ++;
        }

        return null;
    }

    private static Object getValue(ImportExcel annotation, Object cell){
        // 取得默认值
        if (cell == null) {
            cell = annotation.defaultValue();
        }
        // 没有默认值，返回null
        if (cell == "") {
            return null;
        }
        final Class<? extends AbstractExcelFormat> method = annotation.format().method();
        try {
            final AbstractExcelFormat abstractExcelFormat = method.getConstructor().newInstance();
            return abstractExcelFormat.getValue(cell, annotation.format());
        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException("获取 value 失败");
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
//        final List<Human> humans = importExcel(Human.class, null, "1");
//        File file = new File("file/test/t2.xlsx");
//        ExcelData excelData = new ExcelData(file, 0);
//        excelData.getRow(10);、
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
        File file = new File("file/test/t2.xlsx");
        final List<Human> humans = importExcel(Human.class, file, "Sheet2");
        System.out.println(JSONObject.toJSONString(humans));
    }

}
