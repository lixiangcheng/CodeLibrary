package com.lee.business.jssdk.service;

import com.lee.business.common.model.User;
import com.lee.util.ImageUtils;
import com.lee.util.PropertyUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lixiangcheng on 16/4/5.
 */
@Service("attacheService")
public class AttacheService {
    @Autowired
    private HttpServletRequest request;

    /**
     *
     * @param bigType 区分 地板家具.其他
     * @param type
     * @param url
     * @param user
     * @param pictureId
     * @throws IOException
     */
	public void uploadRecording(String bigType, String type, String url, User user, String pictureId) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");// 时间格式化.
		String yearMonthDay = sdf.format(new Date());
		PropertyUtil p = new PropertyUtil("properties.properties");
		String savePath = "";
		String prise = "";// 文件的格式
		String dir = "";// 文件的格式
		String orgin = "";// 原始文件//图片加水印需要
		if ("voice".equals(type)) {// 读取声音保存路径
			savePath = p.getValue("audioSavePath");
			prise = ".amr";
			dir = "voice/";
		} else if (type.toLowerCase().indexOf("image") > -1) {// 读取图片保存路径
			savePath = p.getValue("imagesSavePath");
			prise = ".jpg";
			dir = "images/";
			orgin = "org";
		} else if ("video".equals(type)) {
			savePath = p.getValue("videoSavePath");
			dir = "video/";
		} else {
			savePath = p.getValue("fileSavePath");
			dir = "file/";
		}
				
		String attachName = new Date().getTime() + "";// 定义文件名
		String path = savePath + yearMonthDay;// 定义文件夹
		String srcImgPath = path + "/" + attachName + orgin + prise;
		String targerPath = path + "/" + attachName + prise;
		InputStream in = new URL(url).openStream();
		byte[] gif = IOUtils.toByteArray(in);
		// IOUtils.write(gif,new FileOutputStream(new File("c:/test.gif")));
		FileUtils.writeByteArrayToFile(new File(srcImgPath), gif);
		IOUtils.closeQuietly(in);
		if (!"".equals(orgin)) {// 需要加水印)
			markImageByIcon(srcImgPath, targerPath);
		}

		/*Attach attach = new Attach();

		String fileUrl = dir + yearMonthDay + "/" + attachName + prise;// 文件访问的路径
		attach.setFileUrl(fileUrl);
		attach.setAttachName(attachName);
		attach.setFileName(attachName);
		attach.setSavePath(path);
		// 做存表纪录.
		insertAttach(bigType,type, attach, user,pictureId);*/
	}
	
    public void markImageByIcon(String srcImgPath,String targerPath){
    	PropertyUtil p = new PropertyUtil("properties.properties");
		String iconPath = p.getValue("logoPath");
		String targerPath2 =srcImgPath.substring(0,targerPath.lastIndexOf("."))+"rp.jpg";
		
    	ImageUtils.markImageByIcon(iconPath, srcImgPath, targerPath,null,356,800,90);
    	ImageUtils.markImageByIcon(iconPath, srcImgPath, targerPath2,null,0,0,0);//不旋转不缩图只加水印
    }
    
    /*public boolean insertAttach(String bigType,String type, Attach attach, User user,String exustPictureId) {
        if("".equals(bigType) || bigType==null){
            bigType = "diban";
        }
        attach.setCreateDate(new Date());
        if (user != null) {
            attach.setCreatePerson(Integer.valueOf(user.getId().toString()));
        }
        int i = this.attachMapper.insertSelective(attach);
        String pictureType = "";
        String pictureId = "";


        //如果下面插入的表存在变化.或者加 了type ,注意在 删除的时候,也需要加上type
        if ("voice".equals(type)) {//声音
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else{
                pictureType = bigType+"_voice";
                pictureId = (String) request.getSession().getAttribute(bigType+"_voice_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute(bigType+"_voice_pictureId", pictureId);
                }
            }
            //插入商品picture表
            this.wgGoodsPictureService.insertWGGoodsPicture(pictureType, attach.getId(), pictureId);
        } else if (type.indexOf("imageAll") > -1) {//全景图片
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else {
                pictureType = bigType+"_all_image";
                pictureId = (String) request.getSession().getAttribute(bigType+"_all_image_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute(bigType+"_all_image_pictureId", pictureId);
                }
            }
            //插入商品picture表
            this.wgGoodsPictureService.insertWGGoodsPicture(pictureType, attach.getId(), pictureId);
        }else if (type.indexOf("image2All") > -1) {//全景图片 2
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else {
                pictureType = bigType+"_all2_image";
                //全景 公用一个pictureId 只是加了类型做区分
                pictureId = (String) request.getSession().getAttribute(bigType+"_all_image_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute(bigType+"_all_image_pictureId", pictureId);
                }
            }
            //插入商品picture表
            this.wgGoodsPictureService.insertWGGoodsPicture(pictureType, attach.getId(), pictureId);
        } else if (type.indexOf("imageSelf") > -1) {//细节图片
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else {
                pictureType = bigType+"_self_image";
                pictureId = (String) request.getSession().getAttribute(bigType+"_self_image_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute(bigType+"_self_image_pictureId", pictureId);
                }
            }
            //插入商品picture表
            this.wgGoodsPictureService.insertWGGoodsPicture(pictureType, attach.getId(), pictureId);
        } else if (type.indexOf("imageDoor") > -1) {//综合服务商,注册的门头照片
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else {
                pictureType = "zonghe_door_image";
                pictureId = (String) request.getSession().getAttribute("zonghe_door_image_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute("zonghe_door_image_pictureId", pictureId);
                }
            }
            //插入服务商的图片表
            this.providerPictureService.inserProviderPicture(pictureType, attach.getId(), pictureId);
        } else if (type.indexOf("huishouImageDoor") > -1) {//回收商,注册的门头照片
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else {
                pictureType = "huishou_door_image";
                pictureId = (String) request.getSession().getAttribute("huishou_door_image_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute("huishou_door_image_pictureId", pictureId);
                }
            }
            //插入服务商的图片表
            this.providerPictureService.inserProviderPicture(pictureType, attach.getId(), pictureId);
        } else if (type.indexOf("huishouStoreImageDoor") > -1) {//回收商,注册的仓库门头照片
            if(exustPictureId!= null && !"".equals(exustPictureId)){
                pictureId = exustPictureId;
            }else {
                pictureType = "huishou_store_door_image";
                pictureId = (String) request.getSession().getAttribute("huishou_store_door_image_pictureId");
                if (pictureId == null || "".equals(pictureId)) {
                    pictureId = this.providerPictureService.selectGetNextId("PICTURE_ID");
                    request.getSession().setAttribute("huishou_store_door_image_pictureId", pictureId);
                }
            }
            //插入服务商的图片表
            this.providerPictureService.inserProviderPicture(pictureType, attach.getId(), pictureId);
        }
        return i > 0;
    }*/
}
