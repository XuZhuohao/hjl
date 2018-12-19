package com.yui.hjl.excel.util;

import com.alibaba.fastjson.JSON;
import com.yui.hjl.excel.domain.DataInfo;
import com.yui.hjl.excel.domain.ExcelDataInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        File file = new File("D:\\data\\excel\\t1.xlsx");
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(file));
        ExcelDataInfo excelDataInfo = new ExcelDataInfo(excel);
        System.out.println(JSON.toJSONString(excelDataInfo.getListOfName("thisIsAList")));
        System.out.println(JSON.toJSONString(excelDataInfo.getExCelOfNamel("myNmae")));
    }
}
