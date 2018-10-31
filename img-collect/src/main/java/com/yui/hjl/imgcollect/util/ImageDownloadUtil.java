package com.yui.hjl.imgcollect.util;

import com.yui.hjl.imgcollect.entity.ImageEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 图片下载
 *
 * @author XuZhuohao
 * @date 2018-10-22 21:55
 */
@Getter
@Setter
public class ImageDownloadUtil implements Runnable {
    private List<ImageEntity> imageEntities;
    public ImageDownloadUtil(List<ImageEntity> imageEntities){
        this.imageEntities = imageEntities;
    }
    @Override
    public void run() {

    }
}
