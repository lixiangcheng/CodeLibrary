package com.lee.business.jssdk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信sdk
 * Created by leith on 2016/6/24.
 */
@Controller
public class weixinAction {


    @RequestMapping(value = "/wxShow.htm")
    public String wxShow() {
        return "business/jssdk/wxShow";
    }


}
