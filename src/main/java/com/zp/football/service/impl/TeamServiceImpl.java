package com.zp.football.service.impl;

import com.zp.football.dao.TeamDao;
import com.zp.football.domain.Team;
import com.zp.football.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:04
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public Team create(Team team) {
        Team team1 = new Team();
        team1.setTeamNumber(team.getTeamNumber());
        team1.setTeamId(team.getTeamId());
        List<Team> all = findAll(team1);
        if(all!=null && all.size()>0){
            //更新操作
            team.setId(all.get(0).getId());
        }
        return teamDao.saveAndFlush(team);
    }

    @Override
    public List<Team> findAll(Team team) {
        return teamDao.findAll(Example.of(team));
    }

    @Override
    public Team findById(Long id) {
        return teamDao.getOne(id);
    }

    @Override
    public List<Team> saveAll(List<Team> teams) {
        return teamDao.saveAll(teams);
    }
}
