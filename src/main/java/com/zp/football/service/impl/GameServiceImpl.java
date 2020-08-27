package com.zp.football.service.impl;

import com.zp.football.dao.GameDao;
import com.zp.football.domain.Game;
import com.zp.football.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 11:13
 */

@Service
public class GameServiceImpl implements GameService  {
    @Autowired
    private GameDao gameDao;

    @Override
    public Game create(Game game) {
        return gameDao.saveAndFlush(game);
    }

    @Override
    public List<Game> findAll(Game game) {
        return gameDao.findAll(Example.of(game));
    }

    @Override
    public Game findById(String id) {
        return gameDao.getOne(id);
    }

    @Override
    public List<Game> saveAll(List<Game> games){
        return gameDao.saveAll(games);
    }
}
