package com.yui.hjl.imgcollect.controller;

import com.yui.hjl.imgcollect.config.UseProperties;
import com.yui.hjl.imgcollect.entity.vo.EmailVo;
import com.yui.hjl.imgcollect.service.EmailTemplate;
import com.yui.hjl.imgcollect.timer.EmailTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * 邮件发送请求
 *
 * @author XuZhuohao
 * @date 2018-10-31 22:41
 */
@RestController
public class EmailController {

    @Autowired
    EmailTemplate emailTemplate;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UseProperties useProperties;

    @Autowired
    private EmailTimer emailTimer;
    @GetMapping("test")
    public void sendEmailEveryDate(String password) throws URISyntaxException {
        if(password == null) {
            return;
        }
        if (!password.equals(useProperties.getPassword()) && useProperties.getPassword() != null){
            return;
        }
        emailTimer.sendEmail();
    }

}
