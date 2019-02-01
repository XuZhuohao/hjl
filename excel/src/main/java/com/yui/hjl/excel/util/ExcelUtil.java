package com.yui.hjl.excel.util;

import com.alibaba.fastjson.JSON;
import com.yui.hjl.excel.domain.ExcelDataInfo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
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

    public static void main(String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        File file = new File("D:\\data\\excel\\receive.xlsx");
        XSSFWorkbook excel = new XSSFWorkbook(new FileInputStream(file));
        ExcelDataInfo excelDataInfo = new ExcelDataInfo(excel);
        final List<ExcelDataInfo.ExRowInfo> sheet1 = excelDataInfo.getList("Sheet1", 0, 13, 0, -1);
        sheet1.forEach(rows ->{
            System.out.print(rows.getRowIndex() + ":\t");
            rows.getExCellInfo().forEach((index,cell) ->{
                System.out.print(cell.getCol()+":"+cell.getValue()+"\t");
            });
            System.out.println("\n");
        } );
        System.out.println(System.currentTimeMillis() - start);
    }
}
