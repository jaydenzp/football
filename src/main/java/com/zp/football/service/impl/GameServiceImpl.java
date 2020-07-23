package com.zp.football.service.impl;

import com.zp.football.dao.GameDao;
import com.zp.football.domain.Game;
import com.zp.football.service.GameService;
import com.zp.football.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 11:13
 */

@Service
public class GameServiceImpl extends BaseServiceImpl<Game> implements GameService  {

   /* @Autowired
    private GameDao gameDao;

    @Override
    public Game createGame(Game game) {
        //先从数据库查询数据
        Game game2 = new Game();
        game2.setGameId(game.getGameId());
        List<Game> list = this.findGames(game2);
        if (list.size() == 0) {
            //没有查询到数据则新增或者修改数据
            return this.gameDao.saveAndFlush(game);
        }
        return null;
    }

    @Override
    public List<Game> findGames(Game game) {
        Example example = Example.of(game);
        return gameDao.findAll(example);
    }

    @Override
    public Game findById(Long id) {
        return gameDao.getOne(id);
    }

    @Override
    public Game findByGameId(String id) {
        Game game = new Game();
        game.setGameId(id);
        Example example = Example.of(game);
        Optional one = gameDao.findOne(example);
        return null;
    }*/
}
