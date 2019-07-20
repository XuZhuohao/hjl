package com.yui.hjl.love.service.impl;

import com.yui.hjl.love.entity.BaseEntity;
import com.yui.hjl.love.service.BaseInterfaceService;
import org.springframework.data.domain.Example;

import java.util.Optional;

/**
 * @author XuZhuohao
 * @date 2019-07-20 23:26
 */
public abstract class BaseServiceImpl<T extends BaseEntity, ID> implements BaseInterfaceService<T, ID> {


    @Override
    public T get(T entity) {
        Example<T> example = Example.of(entity);
        Optional<T> result = getRepository().findOne(example);
        return result.orElse(null);
    }

    @Override
    public long delete(T entity) {
        Example<T> example = Example.of(entity);
        long count = getRepository().count(example);
        getRepository().delete(entity);
        return count;
    }

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }
}
