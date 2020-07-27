package com.zp.football.dao;

import com.zp.football.dao.base.BaseDao;
import com.zp.football.domain.Game;
import com.zp.football.domain.GameDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/23 11:00
 */
public interface GameDetailDao  extends  JpaRepository<GameDetail,String>{
}
