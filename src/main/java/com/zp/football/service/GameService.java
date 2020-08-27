package com.zp.football.service;

import com.zp.football.domain.Game;
import com.zp.football.service.base.BaseService;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 11:02
 */
public interface GameService {

    Game create(Game game);

    List<Game> findAll(Game game);

    Game findById(String id);

    List<Game> saveAll(List<Game> games);

}
