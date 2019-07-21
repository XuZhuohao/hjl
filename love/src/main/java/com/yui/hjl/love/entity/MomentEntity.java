package com.yui.hjl.love.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author XuZhuohao
 * @date 2019-07-20 22:45
 */
@Setter
@Getter
@SQLDelete(sql = "update mement set deleted=1 where id = ?")
@Entity
@Table(name = "moment")
@Accessors(chain = true)
public class MomentEntity extends BaseEntity {
    /**
     * 主题
     */
    @Column
    private String title;
    /**
     * 内容
     */
    @Column
    private String context;
    /**
     * 发生时间
     */
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date occurrenceTime;
    /**
     * 地点
     */
    @Column
    private String locale;
    /**
     * 附件地址,包括附件和图片，根据属性区分
     */
    @OneToMany(targetEntity = FileEntity.class ,cascade = {CascadeType.DETACH})
    @JoinColumn(name = "moment_id")
    private List<FileEntity> files;

    @ManyToOne(targetEntity = FetterEntity.class, cascade={CascadeType.DETACH})
    @JoinColumn(name = "fetter_id")
    private FetterEntity fetterEntity;
}
