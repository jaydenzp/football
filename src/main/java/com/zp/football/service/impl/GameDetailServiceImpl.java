package com.zp.football.service.impl;

import com.zp.football.dao.GameDetailDao;
import com.zp.football.domain.GameDetail;
import com.zp.football.domain.Team;
import com.zp.football.service.GameDetailService;
import com.zp.football.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:02
 */
public class GameDetailServiceImpl extends BaseServiceImpl<GameDetail> implements GameDetailService  {

  /*  @Autowired
    private GameDetailDao gameDetailDao;

    @Override
    @Transactional
    public GameDetail create(GameDetail gameDetail) {
        //先从数据库查询数据
        GameDetail gameDetail2 = new GameDetail();
        gameDetail2.setGameId(gameDetail.getGameId());
        List<Team> gameDetails = this.findGameDetails(gameDetail);
        if(gameDetails.size()==0) {
            return gameDetailDao.saveAndFlush(gameDetail);
        }
        return  null;
    }

    @Override
    public List<Team> findGameDetails(GameDetail gameDetail) {
        Example example = Example.of(gameDetail);
        return gameDetailDao.findAll(example);
    }

    @Override
    public GameDetail findById(Long id) {
        return gameDetailDao.getOne(id);
    }*/
}
