package com.yui.hjl.love.service;

import com.yui.hjl.love.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author XuZhuohao
 * @date 2019-07-21 11:36
 */
public interface FileService extends BaseInterfaceService<FileEntity, Long> {
    /**
     * 文件保存
     * @param file 文件
     * @return 文件 Entity
     */
    FileEntity upload(MultipartFile file);
}
