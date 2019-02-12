package com.yui.hjl.excel.runtime;

import com.sun.org.apache.xpath.internal.operations.String;
import com.yui.hjl.excel.annotation.ExcelFormat;
import com.yui.hjl.excel.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.lang.reflect.InvocationTargetException;

/**
 * 默认处理
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
public class ExcelFormatBase extends AbstractExcelFormat {
    @Override
    public Object getValue(Object obj, ExcelFormat format) {
        if (obj instanceof Cell) {
            return getValue((Cell) obj);
        } else {
            try {
                return format.type().getConstructor().newInstance((String)obj);
            } catch (Exception e) {
                throw new RuntimeException("获取数据失败！");
            }
        }

    }
}
