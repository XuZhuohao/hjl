package com.yui.hjl.excel.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.util.CellAddress;

/**
 * 数据信息
 *
 * @author XuZhuohao
 * @date 2018/12/19
 */
@Setter
@Getter
public class DataInfo {
    private Boolean isList;

    private CellAddress begin;

    private CellAddress end;

    public DataInfo(CellAddress begin) {
        this.begin = begin;
        this.isList = false;
    }

    public DataInfo(CellAddress begin, CellAddress end) {
        this.begin = begin;
        this.end = end;
        this.isList = true;
    }
}
