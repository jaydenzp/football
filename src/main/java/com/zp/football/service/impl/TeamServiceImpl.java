package com.zp.football.service.impl;

import com.zp.football.dao.GameDetailDao;
import com.zp.football.domain.Team;
import com.zp.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:04
 */
public class TeamServiceImpl implements TeamService {

    @Autowired
    private GameDetailDao gameDetailDao;

    @Override
    public Team create(Team team) {
        return null;
    }

    @Override
    public List<Team> findGames(Team team) {
        return null;
    }

    @Override
    public Team findById(Long id) {
        return null;
    }
}
