package com.zp.football.service;


import com.zp.football.domain.DataDictionary;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/30 11:12
 */
public interface DataDictionaryService {

    DataDictionary create(DataDictionary dataDictionnary);

    List<DataDictionary> findAll(DataDictionary dataDictionnary);

    DataDictionary findById(Long id);

    void deleteById(Long id);
}
