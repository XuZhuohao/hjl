package com.yui.hjl.imgcollect.om.yui.hjl.imgcollect.service.impl;

import com.yui.hjl.imgcollect.util.DateUtil;
import org.junit.Test;

import java.util.Date;

/**
 * DateUtil 测试
 *
 * @author XuZhuohao
 * @date 2018/11/5
 */
public class DateUtilTest {
    @Test
    public void testD () {
        System.out.println(DateUtil.getD(new Date()));
    }

    @Test
    public void testGetDate(){
        System.out.println(DateUtil.getDate("20181002", "yyyyMMdd"));
    }

    @Test
    public void testGetDateByD(){
        System.out.println(DateUtil.getDateByD(11));
    }
}
