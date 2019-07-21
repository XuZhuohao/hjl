package com.yui.hjl.love.service.impl;

import com.yui.hjl.love.config.FileConfig;
import com.yui.hjl.love.entity.FileEntity;
import com.yui.hjl.love.repository.BaseRepository;
import com.yui.hjl.love.repository.FileRepository;
import com.yui.hjl.love.service.FileService;
import com.yui.hjl.love.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author XuZhuohao
 * @date 2019-07-21 11:37
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private FileConfig fileConfig;
    @Override
    public BaseRepository<FileEntity, Long> getRepository() {
        return fileRepository;
    }

    @Override
    public FileEntity upload(MultipartFile file) {
        String path = fileConfig.getPath() + File.separator + System.currentTimeMillis() + file.getOriginalFilename();
        try {
            FileUtils.save(path, file.getInputStream());
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        FileEntity fileEntity = new FileEntity();
        //TODO: type, fileMd5
        fileEntity.setName(file.getOriginalFilename()).setPath(path).setType("").setFileMd5("");
        return save(fileEntity);
    }
}
