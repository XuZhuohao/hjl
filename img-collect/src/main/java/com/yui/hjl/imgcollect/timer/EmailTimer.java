package com.yui.hjl.imgcollect.timer;

import com.yui.hjl.imgcollect.config.UseProperties;
import com.yui.hjl.imgcollect.entity.UserEntity;
import com.yui.hjl.imgcollect.entity.vo.EmailVo;
import com.yui.hjl.imgcollect.repository.UserRepository;
import com.yui.hjl.imgcollect.service.EmailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 定时邮件发送
 *
 * @author XuZhuohao
 * @date 2018/11/1
 */
@Service
@EnableScheduling
public class EmailTimer {
    @Autowired
    EmailTemplate emailTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UseProperties useProperties;

    @Autowired
    private UserRepository userRepository;

    /**
     * 每天六点执行当前程序
     */
    @Scheduled(cron = "0 0 6 * * ? ")
    public void sendEmail(){
        UserEntity userEntityCls = new UserEntity();
        userEntityCls.setSend(true);
        Example<UserEntity> example = Example.of(userEntityCls);
        List<UserEntity> userEntities = userRepository.findAll(example);
        userEntities.forEach(userEntity -> {
            EmailVo emailVo = emailTemplate.getEmailEntity(userEntity.getEmail());
            ResponseEntity<Map> result = this.restTemplate.postForEntity(useProperties.getSendAdminMail(), emailVo, Map.class);
            System.out.println(result);
        });

    }
}
