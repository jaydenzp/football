package com.zp.football.dao;

import com.zp.football.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/22 11:00
 */
public interface GameDao extends JpaRepository<Game,String>{
}
