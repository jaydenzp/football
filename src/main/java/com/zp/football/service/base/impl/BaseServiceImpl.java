package com.zp.football.service.base.impl;

import com.zp.football.dao.base.BaseDao;
import com.zp.football.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 13:56
 */
@Service
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public T create(T t) {
        return this.baseDao.saveAndFlush(t);
    }

    @Override
    public List findAll(T t) {
        Example example = Example.of(t);
        return this.baseDao.findAll(example);
    }

    @Override
    public T findById(Long id) {
        return this.baseDao.getOne(id);
    }
}
