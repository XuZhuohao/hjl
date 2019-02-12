package com.yui.hjl.excel.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 导入excel注解
 *
 * @author XuZhuohao
 * @date 2019/2/1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ImportExcel {
    /**
     * 行记录，{1} 固定取1行的数据， {1， -1} 1到行位
     */
    int[] row() default {1, -1};
    /**
     * 列
     * @return 列
     */
    int column();

    /**
     * 格式预处理规则，{@link ExcelFormat}
     * @return 预处理格式
     */
    ExcelFormat format() default @ExcelFormat(type = String.class);

    /**
     * 默认值
     * @return 默认值
     */
    String defaultValue() default "";
}
