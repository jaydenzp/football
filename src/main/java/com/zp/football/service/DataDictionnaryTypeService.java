package com.zp.football.service;

import com.zp.football.domain.DataDictionnaryType;
import com.zp.football.domain.Game;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/30 10:40
 */
public interface DataDictionnaryTypeService {

    DataDictionnaryType create(DataDictionnaryType dataDictionnaryType);

    List<DataDictionnaryType> findAll(DataDictionnaryType dataDictionnaryType);

    DataDictionnaryType findById(Long id);

}
