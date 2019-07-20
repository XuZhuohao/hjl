package com.yui.hjl.excel.runtime;

/**
 * excel format 处理方法
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
public abstract class AbstractExcelFormat<T> {
    /**
     * 获取value值
     * @return
     */
    public abstract T getValue(Object obj);

}
