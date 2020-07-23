package com.zp.football.service.base;

import com.zp.football.domain.Game;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 13:56
 */
public interface BaseService<T> {

    T create(T game);

    List<T> findAll(T game);

    T findById(Long id);
}
