package com.yui.hjl.imgcollect.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * word
 *
 * @author XuZhuohao
 * @date 2018-10-31 21:26
 */
@Setter
@Getter
@Entity(name = "loveWord")
public class LoveWordEntity extends BaseEntity {

    private String nickName;

    @Column(columnDefinition = "text", nullable = false)
    private String word;

    @Column(nullable = false, length = 3)
    private int useDate;

    @Column(columnDefinition = "bigint default 0")
    private Long times;

    @Column(columnDefinition = "tinyint default 0")
    private boolean isDelete;

}
