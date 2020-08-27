package com.zp.football.service;

import com.zp.football.domain.GameDetail;
import com.zp.football.domain.Team;
import com.zp.football.service.base.BaseService;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:02
 */
public interface GameDetailService extends BaseService<GameDetail> {


    GameDetail create(GameDetail gameDetail);

    List<GameDetail> findAll(GameDetail gameDetail);

    GameDetail findById(String id);

    List<GameDetail> saveAll (List<GameDetail> gameDetails);
}
