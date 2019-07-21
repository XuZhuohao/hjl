package com.yui.hjl.love.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author XuZhuohao
 * @date 2019-07-21 11:58
 */
@Setter
@Getter
@Configuration
public class FileConfig {
    @Value("${file.save.path}")
    public String path;
}
