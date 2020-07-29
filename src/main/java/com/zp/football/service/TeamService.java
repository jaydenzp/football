package com.zp.football.service;

import com.zp.football.domain.Team;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:02
 */
public interface TeamService {

    Team create(Team team);

    List<Team> findGames(Team team);

    Team findById(String id);
}
