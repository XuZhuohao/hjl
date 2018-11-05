package com.yui.hjl.imgcollect.service.impl;

import com.yui.hjl.imgcollect.entity.ImageEntity;
import com.yui.hjl.imgcollect.service.ImageCollect;
import com.yui.hjl.imgcollect.util.ImageCollectUtil;
import com.yui.hjl.imgcollect.util.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图片收集基础类
 *
 * @author XuZhuohao
 * @date 2018-10-22 21:45
 */
abstract class ImageCollectBase implements ImageCollect, Runnable {
    private List<ImageEntity> imageEntityList;
    List<ImageEntity> getImageObject(String patternStr, String MAIN_URL, String DISK_PATH, int... groupIndexes) {
        // 获取图片地址数组
        List<String> urls = ImageCollectUtil.getUrlByPatternOfUrl(patternStr, MAIN_URL,groupIndexes);
        List<ImageEntity> imageObjectList = new ArrayList<>(16);
        // 解析bing网页获取相应的image url, name, suffix属性
        urls.forEach(url -> {
            ImageEntity imageObject = new ImageEntity();
            imageObject.setUrl(MAIN_URL + url);
            // 第一个 全称(index : 0)， 第二个 名字(index : 1)， 第三个 后缀 (index : 2)
            List<String> dealUrls = ImageCollectUtil.getUrlByPatternOfStr("(([^/|.]*)[\\.]{1}(.*))", url, 1,2,3);
            imageObject.setName(dealUrls.get(1));
            imageObject.setSuffix(dealUrls.get(2));
            imageObject.setDiskPath(DISK_PATH + File.separator + dealUrls.get(0));
            imageObjectList.add(imageObject);
        });
        this.imageEntityList = imageObjectList;
        this.run();
        return imageObjectList;
    }
    @Override
    public void run() {
        this.saveImageInfo(this.imageEntityList);
        imageEntityList.forEach(imageEntity -> {
            try {
                // TODO: MD5校验
                Map<String, Object> result = ImageUtil.downloadImage(imageEntity.getUrl(), imageEntity.getDiskPath());
                imageEntity.setSize(Long.valueOf(result.get("size").toString()));
                imageEntity.setDownload(true);
            } catch (IOException e) {
                imageEntity.setDownload(false);
            }
        });
        this.saveImageInfo(this.imageEntityList);
    }

    /**
     * 保存图片数据
     * @param imageEntityList 图片对象list
     */
    public abstract void saveImageInfo(List<ImageEntity> imageEntityList);
}
