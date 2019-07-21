package com.yui.hjl.love.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 文件处理工具类
 *
 * @author XuZhuohao
 * @date 2019-07-21 11:51
 */
@Slf4j
public class FileUtils {
    public static boolean save(String path, InputStream fis){
        try(OutputStream os = new FileOutputStream(new File(path))){
            byte[] buffer = new byte[1024 * 1024 * 5];
            if (fis.read(buffer) != -1){
                os.write(buffer);
            }
            os.flush();
            return true;
        }catch (Exception ex){
            log.error(ex.getLocalizedMessage());
            ex.printStackTrace();
            return false;
        }
    }


}
