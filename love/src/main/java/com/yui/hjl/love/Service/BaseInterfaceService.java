package com.yui.hjl.love.service;

import com.yui.hjl.love.repository.BaseRepository;
import org.springframework.data.domain.Example;

import java.util.Optional;

/**
 * 基本服务接口
 *
 * @author XuZhuohao
 * @date 2019-07-20 23:12
 */
public interface BaseInterfaceService<T, ID> {
    /**
     * 获取 repository
     * @return repository
     */
    BaseRepository<T, ID> getRepository();
    /**
     * 获取一个对象
     * @param entity 查询条件
     * @return entity
     */
    default T get(T entity) {
        Example<T> example = Example.of(entity);
        Optional<T> result = getRepository().findOne(example);
        return result.orElse(null);
    }
    /**
     * 删除
     * @param entity 条件
     * @return 删除记录数
     */
    default long delete(T entity) {
        Example<T> example = Example.of(entity);
        long count = getRepository().count(example);
        getRepository().delete(entity);
        return count;
    }
    /**
     * 保存
     * @param entity 对象
     * @return 保存记录数
     */
    default T save(T entity) {
        return getRepository().save(entity);
    }

}
