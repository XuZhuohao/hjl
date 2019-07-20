package com.yui.hjl.love.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2019-07-21 0:20
 */
@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column
    private String loginId;

    @Column
    private String password;

    @OneToMany
    @JoinColumn(name = "owner_id")
    private List<FetterEntity> fetterEntities;
}
