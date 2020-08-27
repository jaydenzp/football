package com.zp.football.dao;

import com.zp.football.dao.base.BaseDao;
import com.zp.football.domain.Game;
import com.zp.football.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:01
 */
public interface TeamDao extends  JpaRepository<Team,Long>{
}
