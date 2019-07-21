package com.yui.hjl.love.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 图片对象
 *
 * @author XuZhuohao
 * @date 2019-07-21 11:07
 */
@Setter
@Getter
@Entity
@Table(name = "file")
public class FileEntity extends BaseEntity {
    /**
     * 名称
     */
    @Column
    private String name;
    /**
     * 地址
     */
    @Column
    private String path;
    /**
     * 文件 md5
     */
    @Column
    private String fileMd5;
    /**
     * 类型
     */
    @Column
    private String type;
    /**
     * moment
     */
    @ManyToOne(targetEntity = MomentEntity.class, cascade={CascadeType.DETACH})
    @JoinColumn(name = "moment_id")
    private MomentEntity momentEntity;
}
