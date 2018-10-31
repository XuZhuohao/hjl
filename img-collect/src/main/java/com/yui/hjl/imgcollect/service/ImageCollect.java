package com.yui.hjl.imgcollect.service;

import com.yui.hjl.imgcollect.entity.ImageEntity;

import java.util.List;

/**
 * 图片收集
 *
 * @author XuZhuohao
 * @date 2018/10/16
 */
public interface ImageCollect {
    /**
     * 获取图片对象集合 {@link com.yui.hjl.imgcollect.entity.ImageEntity}
     * @return 图片对象集合
     */
    List<ImageEntity> getImageObject();
}
