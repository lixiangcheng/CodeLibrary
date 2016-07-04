package com.lee.business.address.mapper;

import java.util.Map;
import com.lee.business.address.model.City;

import java.util.List;

public interface CityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    //add
    List<City> getCityList(Map<String, String> qMap);

    String getCityByName(String name);

    City selectByName(Map map);
}