package com.yui.hjl.excel.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;

import java.util.Objects;

/**
 * cell 信息
 *
 * @author XuZhuohao
 * @date 2018/12/19
 */
@Setter
@Getter
public class CellInfo {
    private String sheet;
    private Integer row;
    private Integer col;

    public CellInfo(String sheet, Integer row, Integer col) {
        this.sheet = sheet;
        this.row = row;
        this.col = col;
    }

    public CellInfo(Cell cell) {
        this.sheet = cell.getSheet().getSheetName();
        this.col = cell.getColumnIndex();
        this.row = cell.getRowIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){ return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        CellInfo cellInfo = (CellInfo) o;
        return Objects.equals(sheet, cellInfo.sheet) &&
                Objects.equals(row, cellInfo.row) &&
                Objects.equals(col, cellInfo.col);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sheet, row, col);
    }
}
