package com.yui.hjl.imgcollect.service.impl;

import com.yui.hjl.imgcollect.entity.LoveWordEntity;
import com.yui.hjl.imgcollect.entity.UserEntity;
import com.yui.hjl.imgcollect.entity.vo.EmailVo;
import com.yui.hjl.imgcollect.entity.vo.ImageVo;
import com.yui.hjl.imgcollect.repository.LoveWordRepository;
import com.yui.hjl.imgcollect.service.EmailTemplate;
import com.yui.hjl.imgcollect.service.ImageCollect;
import com.yui.hjl.imgcollect.util.DateUtil;
import com.yui.hjl.imgcollect.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * EmailTemplate
 *
 * @author XuZhuohao
 * @date 2018-10-29 22:27
 */
@Service
public class EmailTemplateImpl implements EmailTemplate {
    @Autowired
    private ImageCollect imageCollect;
    @Autowired
    private LoveWordRepository loveWordRepository;
    @Resource
    private TemplateEngine templateEngine;
    @Override
    public EmailVo getEmailEntity(UserEntity user) {
        EmailVo emailEntity = new EmailVo();
        // 获取图片
        Set<ImageVo> imageVos = new HashSet<>(16);
        imageCollect.getImageObject().forEach(imageEntity -> {
            ImageVo imageVo = new ImageVo();
            imageVo.setCid(imageEntity.getName() + "." + imageEntity.getSuffix());
            imageVo.setSrc(imageEntity.getDiskPath());
            imageVos.add(imageVo);
        });
        emailEntity.setImages(imageVos);
        // 获取love word
        String word ;
        try {
            LoveWordEntity loveWordEntity = new LoveWordEntity();
            // 获取日期
            loveWordEntity.setUseDate(DateUtil.getD(new Date()));
            // 删除标志
            loveWordEntity.setDelete(false);
            Example<LoveWordEntity> example = Example.of(loveWordEntity);
            LoveWordEntity theLoveWordEntity = loveWordRepository.findAll(example).get(0);
            // 获取昵称，如果语言昵称为空，取用户昵称
            String nickName = StringUtil.isNotNull(theLoveWordEntity.getNickName())? theLoveWordEntity.getNickName() : user.getNickName();
            word = nickName + "," + theLoveWordEntity.getWord();
            // 语言推送记录次数加1
            theLoveWordEntity.setTimes(theLoveWordEntity.getTimes() + 1);
            loveWordRepository.save(theLoveWordEntity);
        } catch (Exception e){
            word = user.getNickName() + "，我爱你呦!!";
            e.printStackTrace();
        }
        // 构建Template的入参
        Context context = new Context();
        context.setVariable("images", imageVos);
        context.setVariable("word", word);
        // 获取模板+入参生成的html字符串
        String text = templateEngine.process("emailTemplate", context);
        emailEntity.setContent(text);
        emailEntity.setSubject("每日图片");
        emailEntity.setFromUser("786725551@qq.com");
        emailEntity.setToUser(user.getEmail());
        return emailEntity;
    }
}
