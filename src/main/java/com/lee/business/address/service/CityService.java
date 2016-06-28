package com.lee.business.address.service;

import com.lee.business.address.mapper.CityMapper;
import com.lee.business.address.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("cityService")
public class CityService {
	@Autowired
	private CityMapper cityMapper;
	
	public List<City> getCityList(Map<String,String> qMap){
		List<City> listCity  = cityMapper.getCityList(qMap);
		return listCity;
	}
}
