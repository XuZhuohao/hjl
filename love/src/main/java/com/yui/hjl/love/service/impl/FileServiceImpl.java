package com.yui.hjl.love.service.impl;

import com.yui.hjl.love.entity.FileEntity;
import com.yui.hjl.love.repository.BaseRepository;
import com.yui.hjl.love.repository.FileRepository;
import com.yui.hjl.love.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author XuZhuohao
 * @date 2019-07-21 11:37
 */
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Override
    public BaseRepository<FileEntity, Long> getRepository() {
        return fileRepository;
    }

    @Override
    public FileEntity upload(MultipartFile file) {
//        file.getInputStream()
        return null;
    }
}
