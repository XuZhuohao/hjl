package com.yui.hjl.imgcollect.om.yui.hjl.imgcollect.service.impl;

import com.alibaba.fastjson.JSON;
import com.yui.hjl.imgcollect.ImgCollectApplicationTests;
import com.yui.hjl.imgcollect.entity.LoveWordEntity;
import com.yui.hjl.imgcollect.service.LoveWordService;
import com.yui.hjl.imgcollect.util.DateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2018/11/15
 */
public class LoveWordServiceTest extends ImgCollectApplicationTests {
    @Autowired
    LoveWordService loveWordService;

    @Test
    public void testGetLoveWordsByDateOne() {
        LoveWordEntity rtn = loveWordService.getLoveWordsByDate(DateUtil.getDate("20180111", "yyyyMMdd"));
        System.out.println(JSON.toJSONString(rtn));
    }

    @Test
    public void testGetLoveWordsByDate() {
        List<LoveWordEntity> rtn = loveWordService.getLoveWordsByDate(DateUtil.getDate("20180110", "yyyyMMdd"), new Date());
        System.out.println(JSON.toJSONString(rtn));
        List<LoveWordEntity> rtn01 = loveWordService.getLoveWordsByDate(new Date(), DateUtil.getDate("20190110", "yyyyMMdd"));
        System.out.println(JSON.toJSONString(rtn01));
    }

    @Test
    public void testAddLoveWord() {
        LoveWordEntity loveWordEntity = new LoveWordEntity();
        loveWordEntity.setUseDate(11);
        loveWordEntity.setWord("this is the junit test add");
        loveWordEntity.setNickName("nick name");
        loveWordService.addLoveWord(loveWordEntity);
    }
}
