package com.yui.hjl.imgcollect.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 图片实体
 *
 * @author XuZhuohao
 * @date 2018-10-22 21:35
 */
@Setter
@Getter
@Entity(name = "image")
@EntityListeners(AuditingEntityListener.class)
public class ImageEntity extends BaseEntity {
    /**
     * 图片url
     */
    private String url;
    /**
     * 图片名
     */
    private String name;
    /**
     * 图片md5
     */
    private String md5;
    /**
     * 图片磁盘路径
     */
    private String diskPath;
    /**
     * 图片大小
     */
    private Long size;
    /**
     * 图片后缀
     */
    private String suffix;
    /**
     * 是否下载成功
     */
    private boolean isDownload;
}
