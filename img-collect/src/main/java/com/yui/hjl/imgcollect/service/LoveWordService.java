package com.yui.hjl.imgcollect.service;

import com.yui.hjl.imgcollect.entity.LoveWordEntity;
import com.yui.hjl.imgcollect.entity.vo.ResultBaseVo;

import java.util.Date;
import java.util.List;

/**
 * 发送语言服务
 *
 * @author XuZhuohao
 * @date 2018/11/15
 */
public interface LoveWordService {

    LoveWordEntity getLoveWordsByDate(Date date);

    List<LoveWordEntity> getLoveWordsByDate(Date beginDate,Date endDate);

    ResultBaseVo addLoveWord(LoveWordEntity loveWordEntity);

    ResultBaseVo updateLoveWord(LoveWordEntity loveWordEntity);
}
