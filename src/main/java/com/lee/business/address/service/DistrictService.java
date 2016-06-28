package com.lee.business.address.service;

import com.lee.business.address.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class DistrictService {
	@Autowired
	private DistrictMapper districtMapper;
	
	public List<Map<String ,Object>> getDistrictList(Map<String,String> qMap){
		List<Map<String ,Object>> listDistrict  = districtMapper.getDistrictList(qMap);
		return listDistrict;
	}
}
