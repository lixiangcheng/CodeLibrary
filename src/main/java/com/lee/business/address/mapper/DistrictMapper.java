package com.lee.business.address.mapper;


import com.lee.business.address.model.District;

import java.util.List;
import java.util.Map;


public interface DistrictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(District record);

    int insertSelective(District record);

    District selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);
    //add
  //add
    List<Map<String ,Object>> getDistrictList(Map<String, String> qMap);

    District selectByName(Map map);
}