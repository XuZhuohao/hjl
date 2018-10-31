package com.yui.hjl.imgcollect.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 *
 * @author XuZhuohao
 * @date 2018-10-31 22:00
 */
public class DateUtil {

    public static Long getMMdd(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("MMdd");
        return Long.valueOf(sf.format(date));
    }

    /**
     * 获取当前天，1-365（366）
     * @param date
     * @return
     */
    public static int getD(Date date){
        SimpleDateFormat sf = new SimpleDateFormat("D");
        System.out.println(sf.format(date));
        return Integer.valueOf(sf.format(date));
    }

}
