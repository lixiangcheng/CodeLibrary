package com.lee.business.address.mapper;


import com.lee.business.address.model.Province;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);

    Province selectByName(String name);
}