package com.lee.webservice.service;

import javax.jws.WebService;

/**
 * Created by lixiangcheng on 16/6/25.
 */
@WebService
public class Cxfws {

    /**
     * 读取系统发票数据接口
     * @param BUKRS 		公司代码，必填
     * @param VBELN_LOW 	起始单据号(可以是订单，也可以是发货单，按实际情况而定)
     * @param VBELN_HIGH 	终止单据号
     * @param FKDAT_LOW 	起始单据日期
     * @param FKDAT_HIGH 	终止单据日期
     * @param KUNAG_LOW 	起始客户编码
     * @param KUNAG_HIGH 	终止客户编码
     * @param APPLYDAT_LOW	审核通过起始日期（新增） modified in 2015/12/14
     * @param APPLYDAT_HIGH	审核通过截止日期（新增） modified in 2015/12/14
     * @return
     */
    public String testWS(String BUKRS,String VBELN_LOW,String VBELN_HIGH,String FKDAT_LOW,String FKDAT_HIGH,String KUNAG_LOW,String KUNAG_HIGH,
                         String APPLYDAT_LOW,String APPLYDAT_HIGH){
        return "";
    }
}
