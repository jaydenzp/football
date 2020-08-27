package com.zp.football.dao;

import com.zp.football.domain.DataDictionary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/30 10:15
 */
public interface DataDictionaryDao extends JpaRepository<DataDictionary,Long> {
}
