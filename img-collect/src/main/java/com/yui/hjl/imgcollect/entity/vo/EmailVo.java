package com.yui.hjl.imgcollect.entity.vo;

import com.yui.hjl.imgcollect.entity.ImageEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 邮件实体类
 *
 * @author XuZhuohao
 * @date 2018/10/16
 */
@Setter
@Getter
public class EmailVo {

    /**
     * 发件人
     */
    private String fromUser;
    /**
     * 收件人
     */
    private String toUser;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 插入图片
     */
    private Set<ImageVo> images;

    /**
     * 回调地址
     */
    private String callbackDomain;
}
