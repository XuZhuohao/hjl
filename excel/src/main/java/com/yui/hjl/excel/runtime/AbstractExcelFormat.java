package com.yui.hjl.excel.runtime;

import com.yui.hjl.excel.annotation.ExcelFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * excel format 处理方法
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
public abstract class AbstractExcelFormat<T> {
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

    static Object getValue(Cell cell) {
        final Method method = VALUE_METHOD.get(cell.getCellType());
        try {
            return method.invoke(cell);
        } catch (Exception e) {
            System.err.println("反射获取表格的值失败！");
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

    /**
     * 获取value值
     *
     * @return
     */
    public abstract Object getValue(Object obj, ExcelFormat format);

}
