package com.yui.hjl.imgcollect.repository;

import com.yui.hjl.imgcollect.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * image repository
 *
 * @author XuZhuohao
 * @date 2018-10-22 22:44
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
