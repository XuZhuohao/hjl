package com.yui.hjl.imgcollect.repository;

import com.yui.hjl.imgcollect.entity.LoveWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * image repository
 *
 * @author XuZhuohao
 * @date 2018-10-22 22:44
 */
public interface LoveWordRepository extends JpaRepository<LoveWordEntity, Long> {
    /**
     * 利用 UseDat 进行范围查询
     * @param begin 使用日期 开始日期
     * @param end 使用日期 结束日期
     * @return List<LoveWordEntity>
     */
    List<LoveWordEntity> findByUseDateBetween(int begin, int end);

    /**
     * 通过使用日期
     * @param useDate 使用日期
     * @param isDelete 是否删除
     * @return List<LoveWordEntity>
     */
    List<LoveWordEntity> findByUseDateAndAndIsDelete(int useDate, Boolean isDelete);
}
