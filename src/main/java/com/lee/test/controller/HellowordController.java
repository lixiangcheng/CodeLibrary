package com.lee.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lixiangcheng on 16/7/2.
 */
@Controller
public class HellowordController {

    @RequestMapping(value="/hello.htm")
    public String hello(@RequestParam(value = "a",required = false) String a, ModelMap modelMap){
        System.out.println("我进了hello 的控制器方法:"+a);
        modelMap.addAttribute("hellonum","hellonum");
        return "hellow/hello";
    }
}
