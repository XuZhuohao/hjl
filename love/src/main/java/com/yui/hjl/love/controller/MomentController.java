package com.yui.hjl.love.controller;

import com.yui.hjl.love.dto.ResponseBean;
import com.yui.hjl.love.entity.MomentEntity;
import com.yui.hjl.love.service.MomentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author XuZhuohao
 * @date 2019-07-20 23:52
 */
@RestController
@RequestMapping("moment")
public class MomentController {
    @Autowired
    private MomentService momentService;

    @GetMapping("/{id}")
    public MomentEntity get(@PathVariable Long id){
        MomentEntity momentEntity = new MomentEntity();
        momentEntity.setId(id);
        return momentService.get(momentEntity);
    }

    @PostMapping("/save")
    public ResponseBean save(@RequestParam MomentEntity momentEntity){
        MomentEntity entity = momentService.save(momentEntity);
        return ResponseBean.success(entity);
    }
}
