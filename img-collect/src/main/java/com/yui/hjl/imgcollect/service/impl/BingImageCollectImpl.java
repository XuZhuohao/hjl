package com.yui.hjl.imgcollect.service.impl;

import com.yui.hjl.imgcollect.entity.ImageEntity;
import com.yui.hjl.imgcollect.repository.ImageRepository;
import com.yui.hjl.imgcollect.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 图片收集 - bing
 *
 * @author XuZhuohao
 * @date 2018/10/16
 */
@Service
public class BingImageCollectImpl extends ImageCollectBase {
    @Value("${image.bing-main-url}")
    private String mainUrl;
    @Value("${image.bing-disk-path}")
    private String diskPath;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<ImageEntity> getImageObject() {
        String patternStr = "g_img\\=\\{url:\\s*\"([^\"]*)\"";
        /*
         文件夹生成
          */
        // 获取当前年月
        int date = DateUtil.getYYYYMM(new Date());
        // 判断文件夹是否存在
        String imageDirectory = diskPath + File.separator + date;
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            System.out.println("创建文件夹：" + imageDirectory + " - " + directory.mkdirs());
        }
        // 调用基础类方法进行
        return this.getImageObject(patternStr, mainUrl, imageDirectory, 1);
    }

    @Override
    public void saveImageInfo(List<ImageEntity> imageEntityList) {
        imageRepository.saveAll(imageEntityList);
    }
}
