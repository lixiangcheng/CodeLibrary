package com.basic.javabase.singleton;

/**
 * Created by lixiangcheng on 16/7/6.
 */
public class EsingletonTest extends Thread{
    public void run(){
        System.out.println(Esingleton.getEsingleton().hashCode());
    }

    public static  void main(String[] agro){
        EsingletonTest[] EsingletonTest = new EsingletonTest[10];
//        Bsingleton.getBsingleton();
        for (int i=0;i<EsingletonTest.length;i++){
            EsingletonTest[i] = new EsingletonTest();
        }
        for (int i=0;i<EsingletonTest.length;i++){
            EsingletonTest[i].run();
        }
    }
}
