package com.zp.football.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 13:59
 */
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T,Long> {
}
