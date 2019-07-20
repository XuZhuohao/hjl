package com.yui.hjl.love.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * 羁绊
 *
 * @author XuZhuohao
 * @date 2019-07-21 0:24
 */
@Setter
@Getter
@Entity
@Table(name = "fetter")
public class FetterEntity extends BaseEntity {
    /**
     * 所有者
     */
    @ManyToOne(targetEntity = UserEntity.class, cascade={CascadeType.DETACH})
    @JoinColumn(name = "owner_id")
    private UserEntity owner;
    /**
     * moment
     */
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fetter_id")
    private List<MomentEntity> momentEntities;
    /**
     * 名字
     */
    @Column
    private String name;
    /**
     * 形容
     */
    @Column
    private String context;
    /**
     * 图片位置
     */
    @Column
    private String imageUri;

}
