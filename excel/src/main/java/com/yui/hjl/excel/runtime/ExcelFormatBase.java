package com.yui.hjl.excel.runtime;

import com.yui.hjl.excel.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 * 默认处理
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
public class ExcelFormatBase extends AbstractExcelFormat {
    @Override
    public String getValue(Object obj) {
        return ExcelUtil.getValue((Cell) obj);
    }
}
