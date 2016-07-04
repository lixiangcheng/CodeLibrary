package com.lee.business.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leith on 2016/7/4.
 */
@Controller
public class ImgController {

    /**
     * 上传input   type="file" 时，可以预览图片
     */
    @RequestMapping(value = "/uploadPreviewImage",method = RequestMethod.GET)
    public String uploadPreviewImage(){

        return "business/upload/uploadPreviewImage";
    }
}
