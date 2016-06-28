package com.lee.business.address.controller;

import com.lee.business.address.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 地址的
 * Created by leith on 2016/6/24.
 */
public class Address {
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/select_city.htm")
    public String selectCity(@RequestParam HashMap<String, String> qMap, ModelMap modelMap) {
        modelMap.addAttribute("cityList", this.cityService.getCityList(qMap));
        return "weChat/common/select_city";
    }

    /**
     * ajax
     * 方式
     * @return
     */
    @RequestMapping(value = "/select_city_a.htm")
    @ResponseBody
    public HashMap select_city_a(@RequestParam HashMap<String, String> qMap) {
        HashMap map = new HashMap();
        map.put("cityList", this.cityService.getCityList(qMap));
        return map;
    }
}
