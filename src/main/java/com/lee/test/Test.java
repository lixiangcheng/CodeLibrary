package com.lee.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lixiangcheng on 16/4/19.
 */
public class Test {

    public static void  main(String[] args){

        /*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.HOUR_OF_DAY, 3);
        System.out.println(sdf.format(ca.getTime()));*/


        String nickName = "°²±óÎ°";
        byte[] b_text = nickName.getBytes();
        System.out.println(b_text.length);
        for (int i = 0; i < b_text.length; i++)
        {
            if((b_text[i] & 0xF8)== 0xF0){
                for (int j = 0; j < 4; j++) {
                    b_text[i+j]=0x30;
                }
                i+=3;
            }
        }
        nickName = new String(b_text);
        System.out.println(nickName);
    }
}
