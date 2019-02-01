package com.yui.hjl.excel.domain;

import com.yui.hjl.excel.util.ExcelUtil;
import com.yui.hjl.excel.util.PatternUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * excel 数据结构
 *
 * @author XuZhuohao
 * @date 2018/12/19
 */
@Setter
@Getter
public class ExcelDataInfo {
    private Map<String, ExSheetInfo> exSheetInfo;
    private Map<String, XSSFName> xssfName;

    /**
     * 构造函数
     *
     * @param excel excel 对象
     */
    public ExcelDataInfo(XSSFWorkbook excel) {
        // xssfName
        this.xssfName = new HashMap<>(16);
        excel.getAllNames().forEach(xssfName -> this.xssfName.put(xssfName.getNameName(), xssfName));
        // data
        this.exSheetInfo = new HashMap<>(16);
        for (Sheet sheet : excel) {
            ExSheetInfo exSheetInfo = new ExSheetInfo();
            int rowIndex = 0;
            exSheetInfo.setSheetName(sheet.getSheetName());
            exSheetInfo.setExRowInfo(new HashMap<>(16));
            for (Row row : sheet) {
                ExRowInfo exRowInfo = new ExRowInfo();
                exRowInfo.setRowIndex(row.getRowNum());
                exRowInfo.setExCellInfo(new HashMap<>(16));
                for (Cell cell : row) {
                    ExCellInfo exCellInfo = new ExCellInfo();
                    exCellInfo.setCol(cell.getAddress().getColumn());
                    exCellInfo.setRow(cell.getAddress().getRow());
                    exCellInfo.setValue(ExcelDataInfo.getValue(cell));
                    exRowInfo.getExCellInfo().put(exCellInfo.getCol(), exCellInfo);
                }
                exSheetInfo.getExRowInfo().put(exRowInfo.getRowIndex(), exRowInfo);
            }
            this.exSheetInfo.put(exSheetInfo.getSheetName(), exSheetInfo);
        }
    }

    public String getValue(CellInfo cellInfo) {
        return this.getExSheetInfo().get(cellInfo.getSheet())
                .getExRowInfo().get(cellInfo.getRow())
                .getExCellInfo().get(cellInfo.getCol())
                .getValue();
    }
    public ExCellInfo getCell(CellInfo cellInfo){
        return this.getExSheetInfo().get(cellInfo.getSheet()).getExRowInfo().get(cellInfo.getRow()).getExCellInfo().get(cellInfo.getCol());
    }
    public ExRowInfo getRow(CellInfo cellInfo) {
        return this.getExSheetInfo().get(cellInfo.getSheet()).getExRowInfo().get(cellInfo.getRow());
    }

    public ExSheetInfo getSheet(String name) {
        return this.getExSheetInfo().get(name);
    }
    /**
     * 通过 名称 获取数据
     * @param name 名称
     * @return List<ExRowInfo>， 为 null 调用 {@link com.yui.hjl.excel.domain.ExcelDataInfo#getExCelOfName(java.lang.String)}
     */
    public List<ExRowInfo> getListOfName(String name) {
        final XSSFName xssfName = this.getXssfName().get(name);
        final String refersToFormula = xssfName.getRefersToFormula();
        if (!refersToFormula.contains(":")) {
            return null;
        }
        final CellInfo[] cellInfo = this.getCellInfoDouble(refersToFormula);
        return this.getList(cellInfo[0].getSheet(), cellInfo[0].getCol(), cellInfo[1].getCol(), cellInfo[0].getRow(), -1);
    }

    /**
     *  通过 名称 获取数据
     * @param name 名称
     * @return ExCellInfo
     */
    public ExCellInfo getExCelOfName(String name) {
        final XSSFName xssfName = this.getXssfName().get(name);
        final String refersToFormula = xssfName.getRefersToFormula();
        return this.getCell(this.getCellInfo(refersToFormula));
    }
    /**
     * 从表达获取两个cellInfo
     * @param addresses 地址表达式， 如 Sheet1!$A$3:$C$3
     * @return
     */
    private CellInfo[] getCellInfoDouble(String addresses) {
        final List<String> result = PatternUtil.getStringByPattern("([^!]*)!\\$([A-Z]*)\\$([0-9]*):\\$([A-Z]*)\\$([0-9]*)", addresses, 1, 2, 3, 4, 5);
        final CellAddress address1 = new CellAddress(result.get(1) + result.get(2));
        final CellAddress address2 = new CellAddress(result.get(3) + result.get(4));
        CellInfo[] reuslt = new CellInfo[2];
        reuslt[0] = new CellInfo(result.get(0), address1.getRow(), address1.getColumn());
        reuslt[1] = new CellInfo(result.get(0), address2.getRow(), address2.getColumn());
        return reuslt;
    }
    private CellInfo getCellInfo(String addresses) {
        final List<String> result = PatternUtil.getStringByPattern("([^!]*)!\\$([A-Z]*)\\$([0-9]*)", addresses, 1, 2, 3);
        final CellAddress address1 = new CellAddress(result.get(1) + result.get(2));
        return new CellInfo(result.get(0), address1.getRow(), address1.getColumn());
    }
    /**
     * 获取一个方形区域的所有cell
     * @param sheetName sheetName
     * @param colBegin col 开始位置
     * @param colEnd col 结束位置
     * @param rowBegin row 开始位置
     * @param rowEnd row 结束位置（为 -1 时取到数据结束）
     * @return 一个方形区域
     */
    public List<ExRowInfo> getList(String sheetName, Integer colBegin, Integer colEnd, Integer rowBegin, Integer rowEnd) {
        List<ExRowInfo> result = new ArrayList<>();
        Map<Integer, ExRowInfo> allRow = this.getSheet(sheetName).getExRowInfo();
        if (rowEnd == -1) {
            rowEnd = rowBegin + allRow.size();
        }
        for (int i = rowBegin; i <= rowEnd; i++) {
            ExRowInfo exRowInfo = allRow.get(i);
            if (exRowInfo == null) {
                continue;
            }
            Map<Integer, ExCellInfo> allCell = exRowInfo.getExCellInfo();
            // result
            ExRowInfo exRowResult = new ExRowInfo();
            exRowResult.setRowIndex(exRowInfo.getRowIndex());
            exRowResult.setExCellInfo(new HashMap<>(16));
            for (int j = colBegin; j <= colEnd; j++) {
                if (allCell.get(j) == null) {
                    continue;
                }
                exRowResult.getExCellInfo().put(allCell.get(j).getCol(), allCell.get(j));
            }
            result.add(exRowResult);
        }
        return result;
    }
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
    /**
     * =======================内部类=========================
     */
    @Setter
    @Getter
    public class ExSheetInfo {
        private String sheetName;
        private Map<Integer, ExRowInfo> exRowInfo;
    }

    @Setter
    @Getter
    public class ExRowInfo {
        private Integer rowIndex;
        private Map<Integer, ExCellInfo> exCellInfo;
    }

    @Setter
    @Getter
    public class ExCellInfo {
        private Integer row;
        private Integer col;
        private String value;
    }

}
