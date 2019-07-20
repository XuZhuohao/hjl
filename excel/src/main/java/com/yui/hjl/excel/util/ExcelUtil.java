package com.yui.hjl.excel.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * excel
 *
 * @author XuZhuohao
 * @date 2018/12/19
 */
public class ExcelUtil {
    private static Map<CellType, Method> VALUE_METHOD;

    static {
        VALUE_METHOD = new HashMap<>(16);
        try {
            VALUE_METHOD.put(CellType.BOOLEAN, Cell.class.getMethod("getBooleanCellValue"));
            VALUE_METHOD.put(CellType.ERROR, Cell.class.getMethod("getErrorCellValue"));
            VALUE_METHOD.put(CellType.NUMERIC, Cell.class.getMethod("getNumericCellValue"));
            VALUE_METHOD.put(CellType.STRING, Cell.class.getMethod("getStringCellValue"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getValue(Cell cell) {
        final Method method = VALUE_METHOD.get(cell.getCellType());
        if (method == null) {
            return "";
        } else {
            try {
                return String.valueOf(method.invoke(cell));
            } catch (Exception e) {
                System.err.println("反射获取表格的值失败！");
                throw new RuntimeException(e.getLocalizedMessage());
            }
        }
    }
    public static void main(String[] args) throws Exception {
//        final long start = System.currentTimeMillis();
//        File file = new File("D:\\data\\excel\\receive.xlsx");
//        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(file));
//        ExcelDataInfo excelDataInfo = new ExcelDataInfo(excel);
//        final List<ExcelDataInfo.ExRowInfo> sheet1 = excelDataInfo.getList("Sheet1", 0, 13, 0, -1);
//        sheet1.forEach(rows ->{
//            System.out.print(rows.getRowIndex() + ":\t");
//            rows.getExCellInfo().forEach((index,cell) ->{
//                System.out.print(cell.getCol()+":"+cell.getValue()+"\t");
//            });
//            System.out.println("\n");
//        } );
//        System.out.println(System.currentTimeMillis() - start);
        Class clzz = Integer.class;
        System.out.println(clzz.equals(Integer.class));
    }

}
