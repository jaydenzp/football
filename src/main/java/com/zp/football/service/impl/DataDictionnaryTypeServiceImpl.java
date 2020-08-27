package com.zp.football.service.impl;

import com.zp.football.dao.DataDictionnaryTypeDao;
import com.zp.football.domain.DataDictionnaryType;
import com.zp.football.service.DataDictionnaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: zhanpeng
 * @Date: 2020/7/30 11:21
 */
@Service
public class DataDictionnaryTypeServiceImpl implements DataDictionnaryTypeService {

    @Autowired
    private DataDictionnaryTypeDao dataDictionnaryTypeDao;

    @Override
    public DataDictionnaryType create(DataDictionnaryType dataDictionnaryType) {
        DataDictionnaryType dataDictionnaryType1 = new DataDictionnaryType();
        dataDictionnaryType1.setCode(dataDictionnaryType.getCode());
        List<DataDictionnaryType> all = findAll(dataDictionnaryType1);
        if(all!=null && all.size()>0){
            dataDictionnaryType.setId(all.get(0).getId());
            return dataDictionnaryTypeDao.saveAndFlush(dataDictionnaryType);
        }
        return dataDictionnaryTypeDao.saveAndFlush(dataDictionnaryType);
    }

    @Override
    public List<DataDictionnaryType> findAll(DataDictionnaryType dataDictionnaryType) {
        return dataDictionnaryTypeDao.findAll(Example.of(dataDictionnaryType));
    }

    @Override
    public DataDictionnaryType findById(Long id) {
        return dataDictionnaryTypeDao.getOne(id);
    }
}
