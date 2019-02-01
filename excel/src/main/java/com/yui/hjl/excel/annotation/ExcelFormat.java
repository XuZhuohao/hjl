package com.yui.hjl.excel.annotation;

import com.yui.hjl.excel.runtime.AbstractExcelFormat;
import com.yui.hjl.excel.runtime.ExcelFormatBase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel format
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelFormat {
    /**
     * 数据类型
     * @return 数据类型
     */
    Class type();

    /**
     * 格式化文本，如: yyyy-MM-dd
     * @return 格式化文本
     */
    String format() default "";

    /**
     * 处理value的方法实现
     * @return
     */
    Class<? extends AbstractExcelFormat> method() default ExcelFormatBase.class;
}
