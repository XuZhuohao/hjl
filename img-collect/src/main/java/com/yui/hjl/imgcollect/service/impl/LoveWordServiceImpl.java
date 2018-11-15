package com.yui.hjl.imgcollect.service.impl;

import com.yui.hjl.imgcollect.entity.LoveWordEntity;
import com.yui.hjl.imgcollect.entity.vo.ResultBaseVo;
import com.yui.hjl.imgcollect.repository.LoveWordRepository;
import com.yui.hjl.imgcollect.service.LoveWordService;
import com.yui.hjl.imgcollect.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * LoveWordService 实现类
 *
 * @author XuZhuohao
 * @date 2018/11/15
 */
@Service
public class LoveWordServiceImpl implements LoveWordService {
    @Autowired
    LoveWordRepository loveWordRepository;

    @Override
    public LoveWordEntity getLoveWordsByDate(Date date) {
        LoveWordEntity loveWordEntity = new LoveWordEntity();
        loveWordEntity.setUseDate(DateUtil.getD(date));
        loveWordEntity.setDelete(false);
        Example<LoveWordEntity> example = Example.of(loveWordEntity);
        final List<LoveWordEntity> loveWordEntities = loveWordRepository.findAll(example);
        if (loveWordEntities.isEmpty()){
            return null;
        }
        return loveWordEntities.get(0);
    }

    @Override
    public List<LoveWordEntity> getLoveWordsByDate(Date beginDate, Date endDate) {
        if (beginDate.getTime() > endDate.getTime()){
            throw new RuntimeException("开始时间必须小于结束时间！！");
        }
        int begin = DateUtil.getD(beginDate);
        int end = DateUtil.getD(endDate);
        List<LoveWordEntity> result = new ArrayList<>();
        if (begin > end) {
            result.addAll(loveWordRepository.findByUseDateBetween(DateUtil.getD(beginDate), 999));
            result.addAll(loveWordRepository.findByUseDateBetween(0, DateUtil.getD(endDate)));
        } else {
            result.addAll(loveWordRepository.findByUseDateBetween(DateUtil.getD(beginDate), DateUtil.getD(endDate)));
        }
        return result;
    }

    @Override
    public ResultBaseVo addLoveWord(LoveWordEntity loveWordEntity) {
        // 设置原来的为无效
        List<LoveWordEntity> byUseDateAndAndDelete = loveWordRepository.findByUseDateAndAndIsDelete(loveWordEntity.getUseDate(), false);
        if (!byUseDateAndAndDelete.isEmpty()) {
            byUseDateAndAndDelete.get(0).setDelete(true);
            loveWordRepository.save(byUseDateAndAndDelete.get(0));
        }
        // 新增记录
        loveWordRepository.save(loveWordEntity);
        ResultBaseVo resultBaseVo = new ResultBaseVo();
        resultBaseVo.setErrMsg("");
        resultBaseVo.setSuccess(true);
        return resultBaseVo;
    }

    @Override
    public ResultBaseVo updateLoveWord(LoveWordEntity loveWordEntity) {
        loveWordRepository.save(loveWordEntity);
        return null;
    }
}
