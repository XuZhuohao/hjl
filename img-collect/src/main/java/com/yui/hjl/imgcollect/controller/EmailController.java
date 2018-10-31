package com.yui.hjl.imgcollect.controller;

import com.yui.hjl.imgcollect.entity.vo.EmailVo;
import com.yui.hjl.imgcollect.service.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
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

    private String host = "http://localhost:8081";
    @GetMapping("test")
    public void sendEmailEveryDate() throws URISyntaxException {
        EmailVo emailVo = emailTemplate.getEmailEntity("651334311@qq.com");
        String uri = host + "/email/send";
//        RequestEntity<EmailVo> requestEntity = RequestEntity.post(new URI(uri)).body(emailVo);
//        ResponseEntity<Map> result = this.restTemplate.exchange(requestEntity, Map.class);

        ResponseEntity<Map> result = this.restTemplate.postForEntity(uri, emailVo, Map.class);
        System.out.println(result);
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }
}
