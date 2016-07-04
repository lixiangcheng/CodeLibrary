package com.lee.business.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leith on 2016/7/4.
 * 创建和编辑的弹出窗
 */
@Controller
@RequestMapping(value = "/bootstrap")
public class CUController {

    @RequestMapping(value = "/cu/list",method = RequestMethod.GET)
    public String list(){
        return "business/bootstrap/list";
    }

    @RequestMapping(value = "/cu/edit",method = RequestMethod.GET)
    public String edit(){
        return "business/bootstrap/edit";
    }

}
