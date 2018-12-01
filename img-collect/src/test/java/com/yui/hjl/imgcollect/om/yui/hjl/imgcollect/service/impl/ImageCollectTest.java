package com.yui.hjl.imgcollect.om.yui.hjl.imgcollect.service.impl;

import com.alibaba.fastjson.JSON;
import com.yui.hjl.imgcollect.entity.ImageEntity;
import com.yui.hjl.imgcollect.entity.LoveWordEntity;
import com.yui.hjl.imgcollect.entity.UserEntity;
import com.yui.hjl.imgcollect.entity.vo.ImageVo;
import com.yui.hjl.imgcollect.repository.LoveWordRepository;
import com.yui.hjl.imgcollect.repository.UserRepository;
import com.yui.hjl.imgcollect.service.ImageCollect;
import com.yui.hjl.imgcollect.timer.EmailTimer;
import com.yui.hjl.imgcollect.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.*;

/**
 * 图片收集测试
 *
 * @author XuZhuohao
 * @date 2018-10-22 22:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageCollectTest {
    @Autowired
    private ImageCollect imageCollect;
    @Autowired
    private LoveWordRepository loveWordRepository;
    @Resource
    private TemplateEngine templateEngine;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailTimer emailTimer;

    @Test
    public void test1() {
        List<ImageEntity> imageEntities = imageCollect.getImageObject();
        imageEntities.forEach(imageEntity -> System.out.println(JSON.toJSONString(imageEntity)));
    }

    @Test
    public void testTemplate() {
        Context context = new Context();
        context.setVariable("word", "宝贝，" + "wo ai 你");
        List<String> images = new ArrayList<>(16);
        String cid = "cid:";
        images.add(cid + "image01");
        images.add(cid + "image03");
        images.add(cid + "image02");
        context.setVariable("images", images);
        String text = templateEngine.process("emailTemplate", context);
        System.out.println(text);
    }

    @Test
    public void testTemplate02() {
        Context context = new Context();
        context.setVariable("word", "宝贝，" + "wo ai 你");
        Set<ImageVo> imageVos = new HashSet<>(16);
        ImageVo imageVo = new ImageVo();
        imageVo.setSrc("t1");
        imageVo.setCid("cid1");
        ImageVo imageVo2 = new ImageVo();
        imageVo2.setSrc("t2");
        imageVo2.setCid("cid2");
        ImageVo imageVo3 = new ImageVo();
        imageVo3.setSrc("t3");
        imageVo3.setCid("cid3");
        imageVos.add(imageVo);
        imageVos.add(imageVo2);
        imageVos.add(imageVo3);
        context.setVariable("images", imageVos);
        String text = templateEngine.process("emailTemplate", context);
        System.out.println(text);
    }

    @Test
    public void testWordEntity() {
        LoveWordEntity loveWordEntity = new LoveWordEntity();
        loveWordEntity.setUseDate(DateUtil.getD(new Date()));
        loveWordEntity.setWord("我爱你呀！！");
        loveWordEntity.setDelete(true);
        loveWordEntity.setNickName("test01");
        loveWordRepository.save(loveWordEntity);
    }

    @Test
    public void testUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("admin");
        userEntity.setSend(true);
        userEntity.setEmail("651334311@qq.com");
        userEntity.setNickName("xzh");
        userRepository.save(userEntity);
    }

    @Test
    public void testTimer() {
        emailTimer.sendEmail();
    }
}
