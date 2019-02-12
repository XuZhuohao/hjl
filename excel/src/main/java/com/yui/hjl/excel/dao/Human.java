package com.yui.hjl.excel.dao;

import com.yui.hjl.excel.annotation.ExcelFormat;
import com.yui.hjl.excel.annotation.ImportExcel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author XuZhuohao
 * @date 2019/2/1
 */
@Setter
@Getter
public class Human {
    @ImportExcel(column = 1,row={1})
    private String name;
    @ImportExcel(column = 2)
    private int age;
    @ImportExcel(column = 3)
    private String adress;
/*    @ImportExcel(column = 4, format = @ExcelFormat(type = Date.class, format = "yyyy年m月DD日"))
    private Date birthday;
    @ImportExcel(column = 5)
    private Date birthday02;*/
}
