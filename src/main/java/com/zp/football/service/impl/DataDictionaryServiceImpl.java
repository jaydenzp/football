package com.zp.football.service.impl;

import com.zp.football.dao.DataDictionaryDao;
import com.zp.football.domain.DataDictionary;
import com.zp.football.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/30 11:20
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private DataDictionaryDao dataDictionaryDao;

    @Override
    public DataDictionary create(DataDictionary dataDictionnary) {
        DataDictionary dataDictionary2 = new DataDictionary();
        dataDictionary2.setCode(dataDictionnary.getCode());
        dataDictionary2.setDateType(dataDictionnary.getDateType());
        List<DataDictionary> all = dataDictionaryDao.findAll(Example.of(dataDictionary2));
        if(all!=null && all.size()>0) {
            dataDictionnary.setId(all.get(0).getId());
            return dataDictionaryDao.saveAndFlush(dataDictionnary);
        }else {
            return dataDictionaryDao.saveAndFlush(dataDictionnary);
        }
    }

    @Override
    public List<DataDictionary> findAll(DataDictionary dataDictionnary) {
        return dataDictionaryDao.findAll(Example.of(dataDictionnary));
    }

    @Override
    public DataDictionary findById(Long id) {
        return dataDictionaryDao.getOne(id);
    }

    @Override
    public void deleteById(Long id) {
        dataDictionaryDao.deleteById(id);
    }
}
