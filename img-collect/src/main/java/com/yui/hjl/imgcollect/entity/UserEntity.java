package com.yui.hjl.imgcollect.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 用户
 *
 * @author XuZhuohao
 * @date 2018/11/1
 */
@Setter
@Getter
@Entity(name = "user")
public class UserEntity extends BaseEntity {

    private String username;

    private String email;

    @Column(columnDefinition = "tinyint default 0")
    private boolean isSend;
    
    private String nickName;
}
