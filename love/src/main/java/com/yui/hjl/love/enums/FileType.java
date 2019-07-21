package com.yui.hjl.love.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuZhuohao
 * @date 2019-07-21 11:13
 */
public enum FileType {
    /**
     * 图片
     */
    IMAGE("jpg", "jpeg", "png", "gif");

    private String suffixes;
    private static Map<String, FileType> FILE_TYPE_MAP;

    FileType(String... suffixes) {
        StringBuilder result = new StringBuilder();
        for (String suffix : suffixes) {
            result.append("|").append(suffix);
        }
        this.suffixes = result.toString().replaceFirst("\\|", "");;
    }


    public String getSuffixes() {
        return this.suffixes;
    }

    static {
        FILE_TYPE_MAP = new HashMap<>(16);
        for (FileType fileType : FileType.values()) {
            String splitStr = "\\|";
            for (String suffix : fileType.getSuffixes().split(splitStr)) {
                FILE_TYPE_MAP.put(suffix, fileType);
            }
        }
    }

    public static FileType getFileType(String suffix) {
        return FILE_TYPE_MAP.get(suffix);
    }



}
