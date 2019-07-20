package com.yui.hjl.excel.domain;

import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author XuZhuohao
 * @date 2019/2/1
 */
@Getter
public class ExcelData {
    private XSSFWorkbook excel;
    private XSSFSheet sheet;
    private Row row;
    private Cell cell;

    private int sheetIndex;
    private int rowIndex;
    private int cellIndex;

    public ExcelData(File file, String startSheetName){
        try {
            this.excel = new XSSFWorkbook(new FileInputStream(file));
            this.getSheet(startSheetName);
            init();
        } catch (IOException e) {
            throw new RuntimeException("Excel 声明出错");
        }
    }

    public ExcelData(File file, int startSheet){
        try {
            this.excel = new XSSFWorkbook(new FileInputStream(file));
            this.getSheet(startSheet);
            init();
        } catch (IOException e) {
            throw new RuntimeException("Excel 声明出错");
        }
    }

    private void init(){
        this.getRow(this.sheet.getFirstRowNum());
        this.getCell(this.row.getFirstCellNum());
    }

    /**
     * 获取下一个 sheet
     * @return XSSFSheet
     */
    public XSSFSheet nextSheet(){
        this.getSheet(this.sheetIndex);
        this.sheetIndex++;
        return this.sheet;
    }
    /**
     * 获取下一个 Row
     * @return Row
     */
    public Row nextRow(){
        this.getRow(this.rowIndex);
        this.rowIndex++;
        return this.row;
    }
    /**
     * 获取下一个 Cell
     * @return Cell
     */
    public Cell nextCell(){
        this.getCell(this.cellIndex);
        this.cellIndex++;
        return this.cell;
    }

    /**
     * 根据 名字 获取 sheet
     * @param sheetName sheet名字
     * @return sheet
     */
    public XSSFSheet getSheet(String sheetName){
        final XSSFSheet tempSheet = this.excel.getSheet(sheetName);
        if (tempSheet == null){
            return null;
        }
        this.sheetIndex = this.excel.getSheetIndex(this.sheet);
        this.sheet = tempSheet;
        return this.sheet;
    }
    /**
     * 根据 index 获取 sheet
     * @param sheetIndex sheet index
     * @return sheet
     */
    public XSSFSheet getSheet(int sheetIndex){
        if (sheetIndex  < this.excel.getFirstVisibleTab() || sheetIndex >= this.excel.getNumberOfSheets()){
            this.sheet = null;
            return null;
        }
        this.sheetIndex = sheetIndex;
        this.sheet = this.excel.getSheetAt(this.sheetIndex);
        this.init();
        return this.sheet;
    }

    /**
     * 根据 index 获取 Row
     * @param index Row index
     * @return Row
     */
    public Row getRow(int index){
        if (index < this.sheet.getFirstRowNum() || index > this.sheet.getLastRowNum()){
            this.row = null;
            return null;
        }
        this.rowIndex = index;
        this.row = this.sheet.getRow(this.rowIndex);
        this.cellIndex = this.row.getFirstCellNum();
        return this.row;
    }

    public Cell getCell(String sheetName, Integer rowIndex, int cellIndex){
        final XSSFSheet tempSheet = this.sheet;
        final Row tempRow = this.row;
        if (sheetName == null){
            sheetName = this.sheet.getSheetName();
        }
        if (sheetName == null){
            sheetName = this.sheet.getSheetName();
        }

        return this.excel.getSheet(sheetName).getRow(rowIndex).getCell(cellIndex);
    }

    /**
     * 根据 index 获取 Cell
     * @param index Cell index
     * @return Cell
     */
    public Cell getCell(int index){
        if (index < this.row.getFirstCellNum() || index  > this.row.getLastCellNum()){
            return null;
        }
        this.cellIndex = index;
        this.cell = this.row.getCell(this.cellIndex);
        return this.cell;
    }

}
