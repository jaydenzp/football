package com.zp.football.service.impl;

import com.zp.football.dao.GameDetailDao;
import com.zp.football.domain.GameDetail;
import com.zp.football.service.GameDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:02
 */
@Service
public class GameDetailServiceImpl implements GameDetailService  {

    @Autowired
    private GameDetailDao gameDetailDao;

    @Override
    public GameDetail create(GameDetail gameDetail) {

        return gameDetailDao.saveAndFlush(gameDetail);
    }

    @Override
    public List<GameDetail> findAll(GameDetail gameDetail) {
        Example example = Example.of(gameDetail);
        return gameDetailDao.findAll(example);
    }

    @Override
    public GameDetail findById(String id) {
        return gameDetailDao.getOne(id);
    }

    @Override
    public List<GameDetail> saveAll(List<GameDetail> gameDetails) {
        return gameDetailDao.saveAll(gameDetails);
    }
}
