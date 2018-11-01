package com.yui.hjl.imgcollect.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 程序配置参数
 *
 * @author XuZhuohao
 * @date 2018/11/1
 */
@Setter
@Getter
@Configuration
public class UseProperties {
    @Value("${email.send-admin-mail}")
    private String sendAdminMail ;
}
