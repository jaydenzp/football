package com.zp.football.service.impl;

import com.zp.football.dao.TeamDao;
import com.zp.football.domain.Team;
import com.zp.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:04
 */
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public Team create(Team team) {
        return teamDao.saveAndFlush(team);
    }

    @Override
    public List<Team> findGames(Team team) {
        return teamDao.findAll(Example.of(team));
    }

    @Override
    public Team findById(String id) {
        return teamDao.getOne(id);
    }
}
