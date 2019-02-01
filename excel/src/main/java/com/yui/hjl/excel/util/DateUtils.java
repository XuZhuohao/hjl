package com.yui.hjl.excel.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author xuzhuohao
 */
public class DateUtils {
    /**
     * 添加方法，时间转换
     *
     * @param resultType 转换的结果类型，对象需要拥有需要 valueOf(String) 方法
     * @param date       日期
     * @param format     格式化格式
     * @param <T>        结果泛型
     * @return 返回格式化后结果
     */
    public static <T> T getDateFormat(Class<T> resultType, Date date, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            if (resultType.equals(String.class)) {
                return (T) sf.format(date);
            }
            Method method = resultType.getMethod("valueOf", String.class);
            final Object result = method.invoke(null, sf.format(date));
            return (T) result;
        } catch (Exception e) {
            throw new RuntimeException("时间转换异常：" + e);
        }
    }

    /**
     * 时间计算
     * @param date 时间
     * @param add 增加的值(可以输入负数)
     * @param unit 单位： yyyy 年， MM 月， dd 日， HH 小时， mm 分钟， ss 秒
     * @return
     */
    public static Date getDateCalculate(Date date, int add, String unit){
        SimpleDateFormat sf = new SimpleDateFormat(unit);
        int unitNumber = Integer.valueOf(sf.format(date));
        unitNumber += add;
        sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        final int i = "yyyyMMdd HH:mm:ss".indexOf(unit);
        String formatDate = sf.format(date);
        formatDate = formatDate.substring(0, i) + unitNumber + formatDate.substring(i+unit.length());
        try {
            return sf.parse(formatDate);
        } catch (ParseException e) {
            throw new RuntimeException("时间计算异常：" + e.getLocalizedMessage());
        }
    }

    public static Date getDate(String dateStr, String format){
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try {
            return sf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期转换失败!");
        }
    }
}
