package com.yui.hjl.imgcollect.service;

import com.yui.hjl.imgcollect.entity.UserEntity;
import com.yui.hjl.imgcollect.entity.vo.EmailVo;

/**
 * 邮件模板构成
 *
 * @author XuZhuohao
 * @date 2018-10-29 22:16
 */
public interface EmailTemplate {

    EmailVo getEmailEntity(UserEntity toUser);
}
