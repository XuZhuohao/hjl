package com.yui.hjl.love.service.impl;

import com.yui.hjl.love.entity.MomentEntity;
import com.yui.hjl.love.repository.BaseRepository;
import com.yui.hjl.love.repository.MomentRepository;
import com.yui.hjl.love.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author XuZhuohao
 * @date 2019-07-20 23:25
 */
@Service
public class MomentServiceImpl implements MomentService {
    @Autowired
    private MomentRepository momentRepository;
    @Override
    public BaseRepository<MomentEntity, Long> getRepository() {
        return momentRepository;
    }
}
